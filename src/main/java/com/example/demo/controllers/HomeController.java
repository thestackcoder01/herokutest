package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.repositary.UserRepositary;
//import com.example.demo.services.TodoService;

@Controller
public class HomeController {
  @Autowired
  BCryptPasswordEncoder passwordEncoder;
  
  @Autowired
  private UserRepositary userRepository;
  
  @Autowired
//   private TodoService todoService;
  
  @GetMapping("/")
  public String home() {
	  return "redirect:/login";
  }
  @GetMapping("/register")
  public String register(Model model) {
	  return "register";
  }
  @GetMapping("/login")
  public String login() {
	  return "login";
  }
 
  @GetMapping("/welcome")
  public String welcome(Model model) {
//	  model.addAttribute("allTodo", todoService.getAllTodo());
	  return "welcome";
  }
  
  @PostMapping("/register")
  public String registerUser(@ModelAttribute("user") User user) {
	  
	  user.setPassword(passwordEncoder.encode(user.getPassword()));
	  userRepository.save(user);
	  return "redirect:/login";
  }
  
  
  
  
  
  
  
  
  
  
  

  
  
  
  
  
  
  
}
