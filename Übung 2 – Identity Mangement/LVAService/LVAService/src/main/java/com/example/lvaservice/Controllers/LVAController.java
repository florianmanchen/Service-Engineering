package com.example.lvaservice.Controllers;

import com.example.lvaservice.Entities.LVA;
import com.example.lvaservice.Repositories.ExerciseRepository;
import com.example.lvaservice.Repositories.LVALeaderRepository;
import com.example.lvaservice.Repositories.LVARepository;
import com.example.lvaservice.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class LVAController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private LVARepository lvaRepository;

    @Autowired
    private LVALeaderRepository lvaleaderRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/getAllLVA")
    public @ResponseBody Iterable<LVA> getAllLVA() {
        return lvaRepository.findAll();
    }
}
