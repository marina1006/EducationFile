package ru.kata.spring.boot_security.demo.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AdminService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping()
public class AuthController {

  private final UserService userService;
  private final AdminService adminService;

  @Autowired
  public AuthController(UserService userService, AdminService adminService) {
    this.userService = userService;
    this.adminService = adminService;

  }

  @GetMapping("/admin")
  public String showUsers(ModelMap model, Principal principal) {
    model.addAttribute("user", userService.listUsers());
    User byUsername = userService.findByUsername(principal.getName());
    model.addAttribute("byUsername", byUsername);
    return "/user"; //view byUsername of DAO
  }

  @GetMapping("/admin/{id}")
  public String show(@PathVariable Long id, ModelMap model) {
    model.addAttribute("user", userService.getUser(id));
    return "/show"; //1 id user of DAO
  }

  @GetMapping("/admin/new")
  public String newUsers(@ModelAttribute("user") User user) {

    return "/new";
  }

  @GetMapping("/admin/{id}/edit")
  public String edit(ModelMap model, @PathVariable("id") Long id) {
    model.addAttribute("user", userService.getUser(id));
    return "edit";
  }

  @PostMapping("/admin")
  public String saveUsers(@ModelAttribute("user") User user) {
    userService.saveUser(user);
    return "/admin";
  }

  @PatchMapping("/admin/{id}")
  public String updateUsers(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
    userService.update(id,user);
    return "/admin";
  }

  @DeleteMapping("/admin/{id}")
  public String delete(@PathVariable("id") Long id) {
    userService.removeUser(id);
    return "/admin";
  }

  @PostMapping("/user")
  public String performRegistration(@ModelAttribute("user") User user) {


    userService.register(user);

    return "/user";
  }
}
