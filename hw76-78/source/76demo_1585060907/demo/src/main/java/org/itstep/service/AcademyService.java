package org.itstep.service;

import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.repository.GroupRepository;
import org.itstep.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Student getStudent(Integer id) {
        return studentRepository.getOne(id);
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
    public Group findGroup(Integer id) {
        return groupRepository.getOne(id);//findWithStudentsById(id);
    }

    @Transactional(readOnly = true)
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    public Integer save(Group group) {
        return groupRepository.saveAndFlush(group).getId();
    }
}
