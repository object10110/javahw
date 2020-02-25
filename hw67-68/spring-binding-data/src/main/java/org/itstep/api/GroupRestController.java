package org.itstep.api;

import org.itstep.data.Repository;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupRestController {
    final Repository<Group, Integer> groupRepository;

    public GroupRestController(@Qualifier("groupDbRepository") Repository<Group, Integer> groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Transactional(readOnly = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    @Transactional(readOnly = true)
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Group getOneGroup(@PathVariable int id)  {
        return groupRepository.find(id);
    }

    @Transactional
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteGroup(@PathVariable int id)  {
        groupRepository.delete(groupRepository.find(id));
    }

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Group createStudent(@RequestBody Group group) {
        Integer id = groupRepository.save(group);
        return groupRepository.find(id);
    }

    @Transactional
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Group updateStudent(@RequestBody Group group){
        groupRepository.update(group);
        return group;
    }
}
