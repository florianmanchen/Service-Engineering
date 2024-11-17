package com.serviceengeneering.lvaservice.controller;

import com.serviceengeneering.lvaservice.Model.Exercise;
import com.serviceengeneering.lvaservice.Model.Student;
import com.serviceengeneering.lvaservice.repository.ExerciseRepository;
import com.serviceengeneering.lvaservice.repository.LvaRepository;
import com.serviceengeneering.lvaservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exerciseSubmission")
public class ExerciseSubmissionController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LvaRepository lvaRepository;

    @GetMapping("/add")
    public String addExerciseForm(Model model) {
        model.addAttribute("exercise", new Exercise());
        model.addAttribute("lvas", lvaRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        return "exercise-submission";
    }

    @PostMapping("/add")
    public String addExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
        return "redirect:/";
    }
}
