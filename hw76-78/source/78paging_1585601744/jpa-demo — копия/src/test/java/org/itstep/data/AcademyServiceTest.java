package org.itstep.data;

import org.itstep.dto.GroupDto;
import org.itstep.dto.StudentDto;
import org.itstep.dto.TeacherDto;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.model.Teacher;
import org.itstep.repository.GroupRepository;
import org.itstep.repository.StudentRepository;
import org.itstep.repository.TeacherRepository;
import org.itstep.service.AcademyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

// Подключаем mockito
@ExtendWith(MockitoExtension.class)
public class AcademyServiceTest {

    // Мокаем репозиторий
    @Mock
    GroupRepository groupRepository;
    @Mock
    StudentRepository studentRepository;
    @Mock
    TeacherRepository teacherRepository;
    // Внедряем репозиторий в сервис
    @InjectMocks
    AcademyService academyService;

    @Test
    void getStudentDto() {
        when(studentRepository.findById(1))
                .thenReturn(Optional.of(new Student(1, "Вася", "Пупкин",
                        LocalDate.of(2001, 1, 1),
                        new Group(1, "Java summer 2019", null, null))));

        StudentDto studentDto = academyService.getStudentDto(1);

        // проверяем, что метод findById репозитория был вызван ровно 1 раз
        verify(studentRepository, times(1)).findById(1);

        // Проверяем DTO
        assertNotNull(studentDto);
        assertEquals(1, studentDto.getId());
        assertEquals("Вася", studentDto.getFirstName());
        assertEquals("Пупкин", studentDto.getLastName());
        assertEquals("Java summer 2019", studentDto.getGroupName());
        assertEquals(1, studentDto.getGroupId());
    }

    @Test
    void findStudentsDto() {
        Group g = new Group(1, "Java summer 2019", null, null);
        List<Student> list = new ArrayList<>();
        Student s1 = new Student(1, "Вася", "Пупкин",
                LocalDate.of(2001, 1, 1), g);
        Student s2 = new Student(1, "Маша", "Пупкина",
                LocalDate.of(2002, 2, 2), g);
        list.add(s1);
        list.add(s2);
        when(studentRepository.findAll())
                .thenReturn(list);

        List<StudentDto> dtoList = academyService.findStudentsDto();

        // проверяем, что метод findAll репозитория был вызван ровно 1 раз
        verify(studentRepository, times(1)).findAll();

        // Проверяем DTO
        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());

        StudentDto f1 = dtoList.get(0);
        StudentDto f2 = dtoList.get(1);

        //проверяем первого студента
        assertEquals(s1.getId(), f1.getId());
        assertEquals(s1.getFirstName(), f1.getFirstName());
        assertEquals(s1.getLastName(), f1.getLastName());
        assertEquals(s1.getGroup().getName(), f1.getGroupName());
        assertEquals(s1.getGroup().getId(), f1.getGroupId());
        //проверяем второго студента
        assertEquals(s2.getId(), f2.getId());
        assertEquals(s2.getFirstName(), f2.getFirstName());
        assertEquals(s2.getLastName(), f2.getLastName());
        assertEquals(s2.getGroup().getName(), f2.getGroupName());
        assertEquals(s2.getGroup().getId(), f2.getGroupId());
    }

    @Test
    void findGroupsDto(){
        List<Group> gL = new ArrayList<>();

        List<Teacher> tL = new ArrayList<>();
        tL.add(new Teacher(1, "Вася", "Учитель", null));

        List<Student> sL1 = new ArrayList<>();
        List<Student> sL2 = new ArrayList<>();
        Student s1 = new Student(1, "Вася", "Пупкин",
                LocalDate.of(2001, 1, 1), new Group(1,"Group1", null, null));
        Student s2 = new Student(1, "Маша", "Пупкина",
                LocalDate.of(2002, 2, 2), new Group(1,"Group1", null, null));
        sL1.add(s1);
        sL1.add(s2);
        sL2.add(s1);

        Group g1 = new Group(1,"Group1", sL1, tL);
        Group g2 = new Group(2,"Group2", sL2, tL);
        gL.add(g1);
        gL.add(g2);

        when(groupRepository.findAll())
                .thenReturn(gL);

        List<GroupDto> dtoList = academyService.findGroupsDto();
        GroupDto f1 = dtoList.get(0);
        GroupDto f2 = dtoList.get(1);

        assertEquals(g1.getId(), f1.getId());
        assertEquals(g1.getName(), f1.getName());

        //если бы в GroupDto были листы id для студентов и учителей
        //assertEquals(g1.getStudents().stream().map(s -> new StudentDto(s.getId(), s.getFirstName(), s.getLastName(), s.getBirthDate(),
        //       s.getGroup().getId(), s.getGroup().getName()))
        //       .collect(Collectors.toList()), f1.);

        assertEquals(g2.getId(), f2.getId());
        assertEquals(g2.getName(), f2.getName());
    }

    @Test
    void getGroupDto(){
        List<Teacher> tL = new ArrayList<>();
        tL.add(new Teacher(1, "Вася", "Учитель", null));

        List<Student> sL1 = new ArrayList<>();
        Student s1 = new Student(1, "Вася", "Пупкин",
                LocalDate.of(2001, 1, 1), new Group(1,"Group1", null, null));
        Student s2 = new Student(1, "Маша", "Пупкина",
                LocalDate.of(2002, 2, 2), new Group(1,"Group1", null, null));
        sL1.add(s1);
        sL1.add(s2);
        Group g1 = new Group(1,"Group1", sL1, tL);

        when(groupRepository.findById(1))
                .thenReturn(Optional.of(g1));

        GroupDto f = academyService.getGroupDto(1);

        assertNotNull(f);
        assertEquals(g1.getId(), f.getId());
        assertEquals(g1.getName(), f.getName());
    }

    @Test
    void getTeacherDto(){
        List<Group> gL = new ArrayList<>();
        Group g1 = new Group(1,"Group1", null, null);
        Group g2 = new Group(2,"Group2", null, null);
        gL.add(g1);
        gL.add(g2);

        Teacher t = new Teacher(1, "Вася", "Учитель", gL);

        when(teacherRepository.findById(1))
                .thenReturn(Optional.of(t));

        TeacherDto f = academyService.getTeacherDto(1);

        assertNotNull(f);
        assertEquals(t.getId(), f.getId());
        assertEquals(t.getFirstName(), f.getFirstName());
        assertEquals(t.getLastName(), f.getLastName());
        assertEquals(t.getGroups().stream().map(Group::getId)
                .collect(Collectors.toList()), f.getGroupsId());
    }
}
