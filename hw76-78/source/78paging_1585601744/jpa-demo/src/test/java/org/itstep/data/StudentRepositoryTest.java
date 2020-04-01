package org.itstep.data;

import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.repository.GroupRepository;
import org.itstep.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("dev")
@Transactional
@SpringJUnitConfig(locations = "classpath:spring-jdbc.xml")
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;

    @BeforeEach
    void repositoriesNotNull(){
        assertNotNull(studentRepository);
        assertNotNull(groupRepository);
    }

    @Test
    void getAll(){
        List<Student> list = studentRepository.findAll();
        assertNotNull(list);
        assertEquals(2, list.size());
        for (Student s:list) {
            assertNotNull(s.getGroup());
        }
    }

    @Test
    void getOne()
    {
        Student s = studentRepository.getOne(1);
        assertNotNull(s);
        assertEquals(1, s.getId());
        assertNotNull(s.getGroup());
        assertEquals(1, s.getGroup().getId());
    }

    @Test
    void save(){
        Group g = groupRepository.getOne(1);
        int countStudent = studentRepository.findAll().size();
        Student s = new Student(0, "Паша", "Бот", LocalDate.of(1990,4,12), g);
        Integer id = studentRepository.saveAndFlush(s).getId();
        assertEquals(countStudent+1, studentRepository.findAll().size());
        Student fined = studentRepository.getOne(id);
        assertEquals(s.getFirstName(), fined.getFirstName());
        assertEquals(s.getLastName(), fined.getLastName());
        assertEquals(s.getBirthDate(), fined.getBirthDate());
        assertEquals(s.getGroup().getId(), fined.getGroup().getId());
    }
}
