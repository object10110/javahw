package org.itstep.service;

import lombok.extern.slf4j.Slf4j;
import org.itstep.dto.GroupDto;
import org.itstep.dto.StudentDto;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.repository.GroupRepository;
import org.itstep.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class AcademyService {

    final StudentRepository studentRepository;
    final GroupRepository groupRepository;

    @Autowired
    public AcademyService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Transactional(readOnly = true)
    public List<Student> findStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<StudentDto> findStudentsDto() {
        return studentRepository.findAll().stream()
                .map(s -> new StudentDto(s.getId(), s.getFirstName(), s.getLastName(), s.getBirthDate(),
                                         s.getGroup().getId(), s.getGroup().getName()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Student getStudent(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Integer save(Student student) {
        return studentRepository.saveAndFlush(student).getId();
    }

    public void update(Student student) {
        studentRepository.save(student);
    }

    public boolean delete(Integer id) {
        studentRepository.deleteById(id);
        return true;
    }

    @Transactional(readOnly = true)
    public Group getGroup(Integer id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    public Integer save(Group group) {
        return groupRepository.saveAndFlush(group).getId();
    }

    public void update(Group group) {
        groupRepository.save(group);
    }

    public boolean deleteGroup(int id) {
        groupRepository.deleteById(id);
        return true;
    }

    public void save(StudentDto s) {
        Group g = groupRepository.findById(s.getGroup()).orElse(null);
        Student student = new Student(s.getFirstName(), s.getLastName(), s.getBirthDate(), g);
        save(student);
    }

    public void update(StudentDto studentDto) {
        save(new Student(studentDto.getId(), studentDto.getFirstName(), studentDto.getLastName(), studentDto.getBirthDate(),
                getGroup(studentDto.getGroup())));
    }

    public List<GroupDto> findGroupsDto() {
        return groupRepository.findAll()
                .stream().map(g -> new GroupDto(g.getId(), g.getName()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StudentDto getStudentDto(int id) {
        Student student = getStudent(id);
        log.debug(student.toString());
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName(), student.getBirthDate(),
                student.getGroup().getId(), student.getGroup().getName());

    }

    public GroupDto getGroupDto(int id) {
        Group group = getGroup(id);
        return new GroupDto(group.getId(), group.getName());
    }

    public void update(GroupDto groupDto) {
        Group g = getGroup(groupDto.getId());
        g.setName(groupDto.getName());
        save(g);
    }

    public void save(GroupDto groupDto) {
        save(new Group(groupDto.getName()));
    }
}
