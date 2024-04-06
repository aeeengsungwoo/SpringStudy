package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("hi")
    public String nicemeetyou(Model model){
        model.addAttribute("username","성우");
        return "greeting";
    }
    @GetMapping("bye")
    public String bye(Model model){
        model.addAttribute("byement","잘가");
        model.addAttribute("username","성우");
        return "goodbye";
    }

}
