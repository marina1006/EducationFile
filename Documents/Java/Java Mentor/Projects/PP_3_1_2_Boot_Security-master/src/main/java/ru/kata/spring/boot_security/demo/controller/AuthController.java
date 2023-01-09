package ru.kata.spring.boot_security.demo.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/")
public class AuthController {

  private final UserService userService;

  @Autowired
  public AuthController(UserService userService) {
    this.userService = userService;

  }
  @GetMapping("/admin")
  public String showUsers(ModelMap model) {

    model.addAttribute("user", userService.listUsers());

    return "admin/index"; //view byUsername of DAO
  }
  @GetMapping("/admin/{id}")
  public String show(@PathVariable Long id, ModelMap model) {
    model.addAttribute("user", userService.getUser(id));
    return "admin/show"; //1 id user of DAO
  }

  @GetMapping("/admin/new")
  public String newUsers(@ModelAttribute("user") User user) {

    return "admin/new";
  }

  @GetMapping("/admin/{id}/edit")
  public String edit(ModelMap model, @PathVariable("id") Long id) {
    model.addAttribute("user", userService.getUser(id));
    return "admin/edit";
  }

  @PostMapping("/admin")
  public String saveUsers(@ModelAttribute("user") User user) {

    userService.saveUser(user);
    return "admin/index";
  }

  @PatchMapping("/admin/{id}")
  public String updateUsers(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
    userService.update(id,user);
    return "admin/index";
  }

  @DeleteMapping("/admin/{id}")
  public String delete(@PathVariable("id") Long id) {
    userService.removeUser(id);
    return "admin/index";
  }

}
