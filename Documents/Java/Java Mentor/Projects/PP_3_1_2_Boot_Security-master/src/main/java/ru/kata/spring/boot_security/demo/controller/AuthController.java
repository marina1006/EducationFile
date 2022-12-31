package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AdminService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin") // /auth
public class AuthController {

  private final UserService userService;
  private final AdminService adminService;

  @Autowired
  public AuthController(UserService userService,AdminService adminService) {
    this.userService = userService;
    this.adminService = adminService;

  }

  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

  @GetMapping("/registration")
  public String registrationPage(@ModelAttribute("user") User user) {
    return "registration";
  }

  @PostMapping("/registration")
  public String performRegistration(@ModelAttribute("user") User user) {

    userService.register(user);

    return "redirect:/login";
  }
  @GetMapping("/showUserInfo")
  public String showUserInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    System.out.println(user);

    return "user";
  }

  @GetMapping("/admin")
  public String adminPage() {
//    adminService.doAdminStuff();
    return "admin";
  }
}
