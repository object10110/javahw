package org.itstep.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.itstep.dao.AbstractDao;
import org.itstep.dao.GroupDao;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class GroupDaoImpl extends AbstractDao<Group, Integer> implements GroupDao {
    protected GroupDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Group.class);
    }
}
