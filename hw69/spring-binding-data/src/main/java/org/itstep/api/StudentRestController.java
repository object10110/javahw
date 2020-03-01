package org.itstep.api;

import org.itstep.data.Repository;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    Repository<Student, Integer> studentRepository;
    final Repository<Group, Integer> groupRepository;

    @Autowired
    public StudentRestController(@Qualifier("studentDbRepository") Repository<Student, Integer> studentRepository,
                                 @Qualifier("groupDbRepository") Repository<Group, Integer> groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Transactional(readOnly = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getAllStudents()  {
        List<Student> all = studentRepository.findAll();
        for (int i = 0; i < all.size(); i++) {
            all.get(i).setGroup(groupRepository.find(all.get(i).getGroup().getId()));
        }
        return all;
    }

    @Transactional(readOnly = true)
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getOneStudent(@PathVariable int id)  {
        try {
            Student student = studentRepository.find(id);
            student.setGroup(groupRepository.find(student.getGroup().getId()));
            return student;

        }
        catch (Exception ex){
            return  new Student();
        }
    }

    @Transactional
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteStudent(@PathVariable int id)  {
        studentRepository.delete(studentRepository.find(id));
    }

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                 consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        Integer save = groupRepository.save(student.getGroup());
        Group group = groupRepository.find(save);
        student.setGroup(group);
        Integer id = studentRepository.save(student);
        student = studentRepository.find(id);
        student.setGroup(group);
        return student;
    }

    @Transactional
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@RequestBody Student student){
        Integer save = groupRepository.save(new Group(0, student.getGroup().getName()));
        student.setGroup(groupRepository.find(save));
        studentRepository.update(student);
        return student;
    }
}
