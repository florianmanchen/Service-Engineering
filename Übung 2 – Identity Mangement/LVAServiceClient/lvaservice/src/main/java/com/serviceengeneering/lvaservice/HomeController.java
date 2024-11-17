package com.serviceengeneering.lvaservice;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("remoteUser", authentication.getName());
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/api/lvaLeaders")
    public String getAllLvaLeaders(Model model) {
        return "redirect:/lvaLeaders/all";
    }

    @GetMapping("/api/students")
    public String getAllStudents() {
        return "redirect:/students/all";
    }

    @GetMapping("/api/lvas")
    public String getAllLVAs() {
        return "redirect:/lvas/all";
    }
    @GetMapping("/api/exerciseSubmission")
    public String getAllExerciseSubmission() {
        return "redirect:/exerciseSubmission/add";
    }
    @GetMapping("/api/exerciseCorrection")
    public String getAllExerciseCorrection() {
        return "redirect:/exerciseCorrection/all";
    }
}