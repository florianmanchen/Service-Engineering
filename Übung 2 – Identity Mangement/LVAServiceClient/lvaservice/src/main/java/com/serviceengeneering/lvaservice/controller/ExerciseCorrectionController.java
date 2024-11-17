package com.serviceengeneering.lvaservice.controller;

import com.serviceengeneering.lvaservice.Model.Exercise;
import com.serviceengeneering.lvaservice.repository.ExerciseRepository;
import com.serviceengeneering.lvaservice.repository.LvaRepository;
import com.serviceengeneering.lvaservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exerciseCorrection")
public class ExerciseCorrectionController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LvaRepository lvaRepository;


    @GetMapping("/all")
    public String listExercise(Model model) {
        model.addAttribute("exercises", exerciseRepository.findAll());
        return "exercise-correction";
    }

    @GetMapping("/edit/{id}")
    public String editExerciseForm(@PathVariable Long id, Model model) {
        model.addAttribute("exercise", exerciseRepository.findById(id).orElseThrow());
        model.addAttribute("lvas", lvaRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        return "exercise-correction-form";
    }

    @PostMapping("/edit/{id}")
    public String editExercise(@PathVariable Long id, Exercise exercise) {
        exercise.setId(id);
        exerciseRepository.save(exercise);
        return "redirect:/exerciseCorrection/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteExercise(@PathVariable Long id) {
        exerciseRepository.deleteById(id);
        return "redirect:/exerciseCorrection/all";
    }
}
