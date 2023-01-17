package ru.kata.spring.boot_security.demo.controller;

import java.security.Principal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller

public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user")
  public String userPage(Principal principal, ModelMap model) {
    model.addAttribute("user", userService.findByUsername(principal.getName()));
    return "user";
  }

  @GetMapping("/index")
  public String indexPage(Principal principal, ModelMap model) {
    model.addAttribute("user", userService.findByUsername(principal.getName()));
    return "index";
  }
}
