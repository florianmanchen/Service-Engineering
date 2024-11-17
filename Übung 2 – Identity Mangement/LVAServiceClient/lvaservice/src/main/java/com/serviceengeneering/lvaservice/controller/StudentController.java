package com.serviceengeneering.lvaservice.controller;

import com.serviceengeneering.lvaservice.Model.Student;
import com.serviceengeneering.lvaservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/all")
    public String listStudents(Model model) {
        model.addAttribute("student", studentRepository.findAll());
        return "students";
    }

    @GetMapping("/add")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/add")
    public String addStudent(Student student) {
        studentRepository.save(student);
        return "redirect:/students/all";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentRepository.findById(id).orElseThrow());
        return "student-form";
    }

    @PostMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Student student) {
        student.setId(id);
        studentRepository.save(student);
        return "redirect:/students/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students/all";
    }
}
