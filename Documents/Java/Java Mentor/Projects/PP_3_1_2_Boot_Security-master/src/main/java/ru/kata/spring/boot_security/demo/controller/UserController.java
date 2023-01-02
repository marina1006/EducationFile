package ru.kata.spring.boot_security.demo.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.service.AdminService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
//@RequestMapping("/user")
public class UserController {
  private final AdminService adminService;
  private final UserService service;

  @Autowired
  public UserController(AdminService adminService,
      UserService service) {
    this.adminService = adminService;
    this.service = service;
  }

  @GetMapping("/showUserInfo")
  public String showUserInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    System.out.println(user);

    return "user";
  }

//  @GetMapping("/admin")
//  public String adminPage() {
//    adminService.doAdminStuff();
//    return "admin";
//  }

}
