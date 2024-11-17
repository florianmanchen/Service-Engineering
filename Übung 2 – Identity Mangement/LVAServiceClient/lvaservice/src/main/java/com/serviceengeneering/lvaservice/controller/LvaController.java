package com.serviceengeneering.lvaservice.controller;

import com.serviceengeneering.lvaservice.Model.Lva;
import com.serviceengeneering.lvaservice.Model.Student;
import com.serviceengeneering.lvaservice.repository.LvaLeaderRepository;
import com.serviceengeneering.lvaservice.repository.LvaRepository;
import com.serviceengeneering.lvaservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lvas")
public class LvaController {

    @Autowired
    private LvaRepository lvaRepository;

    @Autowired
    private LvaLeaderRepository lvaLeaderRepository;
    @Autowired
    private StudentRepository studentRepository;


    // List all LVAs
    @GetMapping("/all")
    public String listLvas(Model model) {
        model.addAttribute("lvas", lvaRepository.findAll());
        return "lvas";
    }

    // Show form to create a new LVA
    @GetMapping("/add")
    public String addLvaForm(Model model) {
        model.addAttribute("lva", new Lva());
        model.addAttribute("lvaLeaders", lvaLeaderRepository.findAll());
        return "lva-form";
    }

    // Handle form submission for creating a new LVA
    @PostMapping("/add")
    public String addLva(Lva lva) {
        lvaRepository.save(lva);
        return "redirect:/lvas/all";
    }

    // Show form to edit an existing LVA
    @GetMapping("/edit/{id}")
    public String editLvaForm(@PathVariable Long id, Model model) {
        Lva lva = lvaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid LVA ID: " + id));
        model.addAttribute("lva", lva);
        model.addAttribute("lvaLeaders", lvaLeaderRepository.findAll());
        return "lva-form";
    }

    // Handle form submission for editing an existing LVA
    @PostMapping("/edit/{id}")
    public String editLva(@PathVariable Long id, @ModelAttribute Lva lva) {
        lva.setId(id);
        lvaRepository.save(lva);
        return "redirect:/lvas/all";
    }

    // Show form to edit an existing LVA
    @GetMapping("/{id}/students")
    public String viewStudnets(@PathVariable Long id, Model model) {
        Lva lva = lvaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid LVA ID: " + id));
        model.addAttribute("lva", lva);
        model.addAttribute("students", lva.getStudents());
        return "lva-students";
    }

    // Delete an LVA
    @GetMapping("/delete/{id}")
    public String deleteLva(@PathVariable Long id) {
        lvaRepository.deleteById(id);
        return "redirect:/lvas/all";
    }

    // Delete a Student for LVA
    @GetMapping("/{id}/students/delete/{idStudent}")
    public String deleteLvaStudents(@PathVariable Long id, @PathVariable Long idStudent) {
        Lva lva = lvaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid LVA ID: " + id));
        List<Student> students = lva.getStudents();
        Student student = studentRepository.findById(idStudent).orElseThrow(() -> new IllegalArgumentException("Invalid Student ID: " + idStudent));
        students.remove(student);
        lva.setStudents(students);
        lvaRepository.save(lva);
        return "redirect:/lvas/" + id + "/students";
    }


    // Show form to create a new LVA
    @GetMapping("/{id}/students/add")
    public String addLvaStudentForm(@PathVariable Long id, Model model) {
        Lva lva = lvaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid LVA ID: " + id));
        lva.setName(null);
        model.addAttribute("lva", lva);
        model.addAttribute("students", studentRepository.findAll());
        return "lva-students-form";
    }

    // Handle form submission for creating a new LVA
    @PostMapping("/{id}/students/add")
    public String addLvaStudent(@PathVariable Long id, Lva lva) {
        Long studnetId = Long.parseLong(lva.getName());
        lva = lvaRepository.findById(id).orElse(null);
        lva.getStudents().add(studentRepository.findById(studnetId).orElseThrow(() -> new IllegalArgumentException("Invalid LVA ID: " + id)));
        lvaRepository.save(lva);
        return "redirect:/lvas/"+id+"/students";
    }


}
