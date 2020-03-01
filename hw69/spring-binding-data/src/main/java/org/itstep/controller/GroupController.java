package org.itstep.controller;

import org.itstep.data.Repository;
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

@RequestMapping("/groups")
@Controller
public class GroupController {
    final Repository<Group, Integer> groupRepository;

    @Autowired
    public GroupController(@Qualifier("groupDbRepository") Repository<Group, Integer> groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping
    public String index(Model model) {
        List<Group> all = groupRepository.findAll();
        model.addAttribute("groups", all);
        return "groups/index";
    }

    @GetMapping("/new")
    public String createStudent() {
        return "groups/create";
    }

    @PostMapping("/new")
    public String createStudent(Group group) {
        int id = groupRepository.save(group);
        return "redirect:/groups/info/" + id;
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable int id, Model model) {
        Group group = groupRepository.find(id);

        model.addAttribute("group", group);
        return "groups/info";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Group group = groupRepository.find(id);
        if (group != null) {
            try {
                groupRepository.delete(group);
            }
            catch (Exception ex){}
        }
        return "redirect:/groups";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Group group = groupRepository.find(id);
        if (group != null) {
            model.addAttribute("group", group);
            return "/groups/update";
        }
        return "redirect:/groups";
    }

    @PostMapping("/update/{id}")
    public String update(Group group) {
        groupRepository.update(group);
        return "redirect:/groups/info/" + group.getId();
    }

}
