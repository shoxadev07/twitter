package uz.salikhdev.springbootinfinity.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.salikhdev.springbootinfinity.mapper.StudentMapper;
import uz.salikhdev.springbootinfinity.model.Student;

import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(String name, String surname, Integer age, String email, String phone) {
        String query = "INSERT INTO students (name, surname, age, email, phone) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, name, surname, age, email, phone);
    }

    public Student findById(Long id) {
        String query = "SELECT * FROM students WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new StudentMapper());
    }

    public List<Student> getAllStudents() {
        String query = "SELECT * FROM students";
        return jdbcTemplate.query(query, new StudentMapper());
    }

    public void deleteStudentById(Long id) {
        String query = "DELETE FROM students WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    public void edit(Long id, String name, String surname, int age, String phone, String email) {
        String query = "UPDATE students SET name = ?, surname = ?, age = ?, email = ?, phone = ? WHERE id = ?";
        jdbcTemplate.update(query, name, surname, age, email, phone, id);
    }
}
