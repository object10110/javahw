package org.itstep.data;

import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class StudentRepository implements org.itstep.data.Repository<Student, Integer> {

    public static final String QUERY_STUDENT_BY_ID = "SELECT id, first_name, last_name, age, \"group\" from students where id=?";
    private JdbcTemplate jdbcTemplate;
    //private DataSourceTransactionManager txManager;

    @Autowired
    public StudentRepository(JdbcTemplate jdbcTemplate
            //,DataSourceTransactionManager txManager
    ) {
        this.jdbcTemplate = jdbcTemplate;
        //this.txManager = txManager;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = SQLException.class, noRollbackFor = FileNotFoundException.class)
    @Override
    public Integer save(Student data) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
//        TransactionStatus transactionStatus = txManager.getTransaction(TransactionDefinition.withDefaults());
//        try {
            jdbcTemplate.update(con -> {
                PreparedStatement ps =
                        con.prepareStatement("insert into students(first_name, last_name, age, \"group\") values(?, ?, ?, ?)",
                                Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, data.getFirstName());
                ps.setString(2, data.getLastName());
                ps.setInt(3, data.getAge());
                ps.setString(4, data.getGroup());
                return ps;
            }, holder);
//            txManager.commit(transactionStatus);
//        } catch (Throwable ex) {
//            txManager.rollback(transactionStatus);
//            System.err.println(ex.getLocalizedMessage());
//        }
        return Objects.requireNonNull(holder.getKey()).intValue();
    }

    @Override
    public void update(Student data) {

    }

    @Override
    public boolean delete(Student data) {
        return false;
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("select id, first_name, last_name, age, \"group\" from students",
                (rs, rowNum) -> new Student(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getString("group")));
    }

    @Transactional(readOnly = true)
    @Override
    public Student find(Integer id) {
        Student student = null;
//        TransactionStatus transactionStatus = txManager.getTransaction(TransactionDefinition.withDefaults());
//        try {
//            txManager.setEnforceReadOnly(true);
            student = jdbcTemplate.queryForObject(QUERY_STUDENT_BY_ID,
                    new Object[]{id},
                    new BeanPropertyRowMapper<>(Student.class)
//                    new RowMapper<Student>() {
//                        @Override
//                        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//                            return new Student(rs.getInt(1), rs.getString(2),
//                                    rs.getString(3), rs.getInt(4), rs.getString(5));
//                        }
//                    }
            );
//            txManager.commit(transactionStatus);
//        } catch (Throwable ex) {
//            txManager.rollback(transactionStatus);
//        }
        return student;
    }

    @Transactional
    @Override
    public boolean deleteById(Integer id) {
        return jdbcTemplate.update("delete from students where id=?", id) != 0;
    }
}
