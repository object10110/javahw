package org.itstep.hellospringboot;

import org.itstep.hellospringboot.domain.entity.Student;
import org.itstep.hellospringboot.domain.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@SpringJUnitWebConfig
@AutoConfigureMockMvc
public class StudentApiTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    public void init() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Вася");
        student.setLastName("Пупкин");
        studentRepository.save(student);
    }

    @Test
    @Order(1)
    @WithMockUser(roles = "ADMIN")
    public void findStudentById() throws Exception {
        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$.firstName", is("Вася")))
                .andExpect(jsonPath("$.lastName", is("Пупкин")));
    }

    @Test
    @Order(2)
    @WithMockUser(roles = "ADMIN")
    public void updateStudent() throws Exception {
        mockMvc.perform(put("/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Маша\", \"lastName\": \"Ефросинина\"}"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$.firstName", is("Маша")))
                .andExpect(jsonPath("$.lastName", is("Ефросинина")));
    }
}
