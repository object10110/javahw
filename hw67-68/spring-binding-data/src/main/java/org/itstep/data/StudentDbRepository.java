package org.itstep.data;

import org.itstep.model.Group;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Repository
public class StudentDbRepository implements Repository<Student, Integer> {
    private JdbcTemplate jdbcTemplate;

    public StudentDbRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public Integer save(Student data) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps =
                    con.prepareStatement("insert into students(first_name, last_name, age, group_id) values(?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, data.getFirstName());
            ps.setString(2, data.getLastName());
            ps.setInt(3, data.getAge());
            ps.setInt(4, data.getGroup().getId());
            return ps;
        }, holder);
        return Objects.requireNonNull(holder.getKey()).intValue();
    }

    @Transactional
    @Override
    public void update(Student data) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("UPDATE students " +
                    "SET first_name = ?, last_name = ?, age = ?, group_id = ? " +
                    "WHERE id = ?");
            ps.setString(1, data.getFirstName());
            ps.setString(2, data.getLastName());
            ps.setInt(3, data.getAge());
            ps.setInt(4, data.getGroup().getId());
            ps.setInt(5, data.getId());
            return ps;
        });
    }

    @Transactional
    @Override
    public boolean delete(Student data) {
        return jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("delete from students where id = ?");
            ps.setInt(1, data.getId());
            return ps;
        }) > 0;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("select id, first_name, last_name, age, group_id from students",
                (rs, rowNum) -> new Student(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        new Group(rs.getInt("group_id"), null)));
    }

    @Transactional(readOnly = true)
    @Override
    public Student find(Integer id) {
        return jdbcTemplate.queryForObject("select * from students where id = ?",
                new Object[]{id},
                (rs, i) -> new Student(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        new Group(rs.getInt("group_id"), null)));
    }
}
