package org.itstep.data;

import org.itstep.model.Group;
import org.itstep.model.Teacher;
import org.itstep.repository.GroupRepository;
import org.itstep.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("dev")
@Transactional
@SpringJUnitConfig(locations = "classpath:spring-jdbc.xml")
public class TeacherRepositoryTest {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @BeforeEach
    void repositoriesNotNull(){
        assertNotNull(teacherRepository);
        assertNotNull(groupRepository);
    }

    @Test
    void getAll(){
        List<Teacher> list = teacherRepository.findAll();
        assertNotNull(list);
        assertEquals(1, list.size());
    }

    @Test
    void getOne()
    {
        Teacher s = teacherRepository.getOne(1);
        assertNotNull(s);
        assertEquals(1, s.getId());
    }

    @Test
    void save(){
        Group group1 = groupRepository.getOne(1);
        Group group2 = groupRepository.getOne(2);
        List<Group> listGroup = new ArrayList<>();
        listGroup.add(group1);
        listGroup.add(group2);

        Teacher t = new Teacher(0,"Володя", "Заболотный", listGroup);
        int countBeforeAdd = teacherRepository.findAll().size();
        Integer id = teacherRepository.saveAndFlush(t).getId();
        assertEquals(countBeforeAdd+1, teacherRepository.findAll().size());
        Teacher fined = teacherRepository.getOne(id);
        assertEquals(t.getFirstName(), fined.getFirstName());
        assertEquals(t.getLastName(), fined.getLastName());
        assertEquals(t.getGroups(), fined.getGroups());
    }
}
