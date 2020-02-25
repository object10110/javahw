package org.itstep.controller;

import org.itstep.data.Repository;
import org.itstep.data.StudentMemoryRepository;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/students")
@Controller
public class StudentController {

    final Repository<Student, Integer> studentRepository;
    final Repository<Group, Integer> groupRepository;

    @Autowired
    public StudentController(@Qualifier("studentDbRepository") Repository<Student, Integer> studentRepository,
                             @Qualifier("groupDbRepository") Repository<Group, Integer> groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @GetMapping
    public String index(Model model) {
        List<Student> all = studentRepository.findAll();
        for (int i = 0; i < all.size(); i++) {
            Group group =  groupRepository.find(all.get(i).getGroup().getId());
            String groupName = group.getName();
            all.get(i).getGroup().setName(groupName);
        }
        model.addAttribute("students", all);
        return "students/index";
    }

    @GetMapping("/new")
    public String createStudent() {
        return "students/create";
    }

    @PostMapping("/new")
    public String createStudent(Student student) {
        Integer save = groupRepository.save(new Group(0, student.getGroupName()));
        student.setGroup(new Group(save, student.getGroupName()));
        int id = studentRepository.save(student);
        return "redirect:/students/info/" + id;
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable int id, Model model) {
        Student student = studentRepository.find(id);

        Group group =  groupRepository.find(student.getGroup().getId());
        String groupName = group.getName();
        student.getGroup().setName(groupName);

        model.addAttribute("student", student);
        return "students/info";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Student student = studentRepository.find(id);
        if (student != null) {
            studentRepository.delete(student);
        }
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Student student = studentRepository.find(id);
        if (student != null) {
            student.setGroup(groupRepository.find(student.getGroup().getId()));
            model.addAttribute("student", student);
            return "/students/update";
        }
        return "redirect:/students";
    }

    @PostMapping("/update/{id}")
    public String update(Student student) {
        Integer save = groupRepository.save(new Group(0, student.getGroupName()));
        student.setGroup(new Group(save, student.getGroupName()));

        studentRepository.update(student);
        return "redirect:/students/info/" + student.getId();
    }

}
