package uz.salikhdev.springbootinfinity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.salikhdev.springbootinfinity.model.Student;
import uz.salikhdev.springbootinfinity.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {


    @Autowired
    private StudentRepository repository;


    public void addStudent(String name, String surname, int age, String phone, String email) {
        try {
            repository.save(name, surname, age, email, phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student getStudent(Long id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Student> getAllStudents() {
        return repository.getAllStudents();
    }

    public void deleteStudentById(Long id) {
        repository.deleteStudentById(id);
    }

    public void editStudent(Long id, String name, String surname, int age, String phone, String email) {
        repository.edit(id, name, surname, age, phone, email);
    }
}
