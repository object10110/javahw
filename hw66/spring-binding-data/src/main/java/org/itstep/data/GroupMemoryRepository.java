package org.itstep.data;

import org.itstep.model.Group;
import org.itstep.model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@org.springframework.stereotype.Repository
public class GroupMemoryRepository implements Repository<Group, Integer> {
    private final static List<Group> storage = new CopyOnWriteArrayList<>();
    @Override
    public Integer save(Group data) {
        storage.add(data);
        Integer maxId = 0;
        if (storage.size() != 0) {
            maxId = storage.stream().max(Comparator.comparing(Group::getId)).get().getId();
        }
        data.setId(++maxId);
        return data.getId();
    }

    @Override
    public void update(Group data) {
        Optional<Group> first = storage.stream().filter(s -> s.getId() == data.getId()).findFirst();
        if (first.isPresent()) {
            Group group = first.get();
            group.setName(data.getName());
        }
    }

    @Override
    public boolean delete(Group data) {
        return storage.removeIf(s -> s.getId() == data.getId());
    }

    @Override
    public List<Group> findAll() {
        return storage;
    }

    @Override
    public Group find(Integer id) {
        return storage.stream()
                .filter(s -> s.getId() == id).findFirst().orElse(null);
    }
}
