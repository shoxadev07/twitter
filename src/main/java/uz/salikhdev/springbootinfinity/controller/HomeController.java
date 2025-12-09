package uz.salikhdev.springbootinfinity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.salikhdev.springbootinfinity.model.Student;
import uz.salikhdev.springbootinfinity.service.StudentService;

@Controller
public class HomeController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }

    @PostMapping("/student/add")
    public String addStudent(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam int age,
                             @RequestParam String phone,
                             @RequestParam String email) {
        studentService.addStudent(name, surname, age, phone, email);
        return "redirect:/?added=true";
    }

    @GetMapping("/student/{id}")
    public String detailsStudent(@PathVariable Long id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "details";
    }

     @PostMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "edit";
    }

    @PostMapping("/student/edit/{id}")
    public String editStudent(@PathVariable Long id,
                              @RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam int age,
                              @RequestParam String phone,
                              @RequestParam(required = false) String email) {
        studentService.editStudent(id, name,surname,age,phone,email);
        return "redirect:/student/" + id + "?edited=true";
    }
}
