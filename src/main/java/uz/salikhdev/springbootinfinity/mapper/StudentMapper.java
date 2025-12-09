package uz.salikhdev.springbootinfinity.mapper;


import org.springframework.jdbc.core.RowMapper;
import uz.salikhdev.springbootinfinity.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        int age = rs.getInt("age");
        String phone = rs.getString("phone");
        String email = rs.getString("email");

        return new Student(id, name, surname, age, phone, email);
    }


}
