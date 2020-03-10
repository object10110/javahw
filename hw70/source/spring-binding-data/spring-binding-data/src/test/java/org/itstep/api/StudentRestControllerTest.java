package org.itstep.api;

import org.itstep.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class StudentRestControllerTest {

    RestTemplate restTemplate;
    public static final String BASE_URL = "http://localhost:8080/api/students";

    @BeforeEach
    void init() {
        restTemplate = new RestTemplate();
    }

    @Test
    void getAllStudents() {
        ResponseEntity<Student[]> forEntity = restTemplate.getForEntity(BASE_URL, Student[].class);
        Assertions.assertTrue(forEntity.hasBody());
        Assertions.assertSame(forEntity.getStatusCode(), HttpStatus.OK);
        Student[] body = forEntity.getBody();
        Assertions.assertNotNull(body);
        Arrays.stream(body).forEach(System.out::println);
    }

    @Test
    void getAllStudents2() {
        Student[] body = restTemplate.getForObject(BASE_URL, Student[].class);
        Assertions.assertNotNull(body);
        Arrays.stream(body).forEach(System.out::println);
    }

    @Test
    void getOneStudent() {
        Student body = restTemplate.getForObject(BASE_URL+"/{id}", Student.class, 1);
        Assertions.assertNotNull(body);
        System.out.println("body = " + body);
    }

    @Test
    void removeFirstStudent() {
        restTemplate.delete(BASE_URL+"/{id}", 1);
    }

}
