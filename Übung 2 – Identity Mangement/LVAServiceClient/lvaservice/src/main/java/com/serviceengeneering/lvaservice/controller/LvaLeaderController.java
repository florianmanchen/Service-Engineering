package com.serviceengeneering.lvaservice.controller;

import com.serviceengeneering.lvaservice.Model.LvaLeader;
import com.serviceengeneering.lvaservice.repository.LvaLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lvaLeaders")
public class LvaLeaderController {

    @Autowired
    LvaLeaderRepository lvaLeaderRepository;

    @GetMapping("/all")
    public String listLvaLeaders(Model model) {
        model.addAttribute("lvaLeaders", lvaLeaderRepository.findAll());
        return "lva-leaders";
    }

    @GetMapping("/add")
    public String addLvaLeaderForm(Model model) {
        model.addAttribute("lvaLeader", new LvaLeader());
        return "lva-leader-form";
    }

    @PostMapping("/add")
    public String addLvaLeader(LvaLeader lvaLeader) {
        lvaLeaderRepository.save(lvaLeader);
        return "redirect:/lvaLeaders/all";
    }

    @GetMapping("/edit/{id}")
    public String editLvaLeaderForm(@PathVariable Long id, Model model) {
        model.addAttribute("lvaLeader", lvaLeaderRepository.findById(id).orElseThrow());
        return "lva-leader-form";
    }

    @PostMapping("/edit/{id}")
    public String editLvaLeader(@PathVariable Long id, LvaLeader lvaLeader) {
        lvaLeader.setId(id);
        lvaLeaderRepository.save(lvaLeader);
        return "redirect:/lvaLeaders/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteLvaLeader(@PathVariable Long id) {
        lvaLeaderRepository.deleteById(id);
        return "redirect:/lvaLeaders/all";
    }
}
