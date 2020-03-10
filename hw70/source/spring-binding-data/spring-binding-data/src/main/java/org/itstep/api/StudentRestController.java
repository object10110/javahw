package org.itstep.api;

import org.itstep.data.Repository;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    Repository<Student, Integer> studentRepository;

    @Autowired
    public StudentRestController(Repository<Student, Integer> studentRepository) {
        this.studentRepository = studentRepository;
    }

    @CrossOrigin(origins = {"http://localhost", "https://localhost", "http://localhost:80"})
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents()  {
        return studentRepository.findAll();
    }

    @GetMapping(path = "/{id}", produces ={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Student getOneStudent(@PathVariable int id)  {
        return studentRepository.find(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                 consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        Integer id = studentRepository.save(student);
        return studentRepository.find(id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        studentRepository.deleteById(id);
    }
}
