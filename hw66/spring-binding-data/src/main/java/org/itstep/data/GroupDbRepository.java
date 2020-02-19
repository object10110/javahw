package org.itstep.data;

import org.itstep.model.Group;
import org.itstep.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Repository
public class GroupDbRepository implements Repository<Group, Integer> {
    private JdbcTemplate jdbcTemplate;

    public GroupDbRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer save(Group data) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps =
                    con.prepareStatement("insert into groups(`name`) values(?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, data.getName());
            return ps;
        }, holder);
        return Objects.requireNonNull(holder.getKey()).intValue();
    }

    @Override
    public void update(Group data) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("UPDATE groups " +
                    "SET `name` = ?" +
                    "WHERE id = ?");
            ps.setString(1, data.getName());
            ps.setInt(2, data.getId());
            return ps;
        });
    }

    @Override
    public boolean delete(Group data) {
        return jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("delete from groups where id = ?");
            ps.setInt(1, data.getId());
            return ps;
        }) > 0;
    }

    @Override
    public List<Group> findAll() {
        return jdbcTemplate.query("select * from groups",
                (rs, rowNum) -> new Group(rs.getInt("id"),
                        rs.getString("name")));
    }

    @Override
    public Group find(Integer id) {
        return jdbcTemplate.queryForObject("select * from groups where id = ?",
                new Object[] { id },
                (rs, i) -> new Group(rs.getInt("id"),
                        rs.getString("name")));
    }
}
