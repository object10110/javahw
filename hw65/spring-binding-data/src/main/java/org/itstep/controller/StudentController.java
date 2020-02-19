package org.itstep.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.itstep.data.StudentRepository;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/students")
@Controller
public class StudentController {

    final StudentRepository repository;

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("students", repository.findAll());
        return "students/index";
    }

    @GetMapping("/new")
    public String createStudent() {
        return "students/create";
    }

    @PostMapping("/new")
    public String createStudent(Student student) {
        int id = repository.save(student);
        return "redirect:/students/info/" + id;
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable int id, Model model) {
        model.addAttribute("student", repository.find(id));
        return "students/info";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Student student = repository.find(id);
        if (student != null) {
            repository.delete(student);
        }
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Student student = repository.find(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "/students/update";
        }
        return "redirect:/students";
    }

    @PostMapping("/update/{id}")
    public String update(Student student){
        repository.update(student);
        return "redirect:/students/info/" + student.getId();
    }

}
