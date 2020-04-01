package org.itstep.data;

import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.repository.GroupRepository;
import org.itstep.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GroupRepositoryTest {
    @Autowired
    GroupRepository groupRepository;

    @BeforeEach
    void repositoriesNotNull(){
        assertNotNull(groupRepository);
    }

    @Test
    void getAll(){
        List<Group> list = groupRepository.findAll();
        assertNotNull(list);
        assertEquals(15, list.size());
    }

    @Test
    void getOne()
    {
        Group s = groupRepository.getOne(1);
        assertNotNull(s);
        assertEquals(1, s.getId());
    }

    @Test
    void save(){
        Group g = new Group("Новая группа");
        int countBeforeAdd = groupRepository.findAll().size();
        Integer id = groupRepository.saveAndFlush(g).getId();
        assertEquals(countBeforeAdd+1, groupRepository.findAll().size());
        Group fined = groupRepository.getOne(id);
        assertEquals(g.getName(), fined.getName());
    }
}
