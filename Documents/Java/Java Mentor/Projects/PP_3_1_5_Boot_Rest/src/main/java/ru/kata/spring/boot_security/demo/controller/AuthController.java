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
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/")
public class AuthController {

  private final UserService userService;
  private final RoleService roleService;

  @Autowired
  public AuthController(UserService userService,
      RoleService roleService) {
    this.userService = userService;

    this.roleService = roleService;
  }
  @GetMapping("/admin")
  public String showUsers(ModelMap model,Principal principal) {

    model.addAttribute("user", userService.listUsers());
    User byUserName = userService.findByUsername(principal.getName());
    model.addAttribute("byUserName", byUserName);
    return "main"; //view byUsername of DAO
  }
  @GetMapping("/admin/{id}")
  public String show(@PathVariable Long id, ModelMap model) {
    model.addAttribute("user", userService.getUser(id));
    return "main"; //1 id user of DAO
  }

  @GetMapping("/admin/new")
  public String newUsers(@ModelAttribute("user") User user) {

    return "main";
  }

  @PostMapping("/admin/new")
  public String saveUsers(User user) {

    userService.saveUser(user);
    return "redirect:/admin";
  }
  @GetMapping("/admin/update/{id}")
  public String edit(@PathVariable("id") Long id, ModelMap model){
    model.addAttribute("user", userService.getUser(id));
    return "main";
  }
  @PatchMapping("/admin/update")
  public String updateUsers(User user) {
    userService.saveUser(user);
    return "redirect:/admin";
  }
  @GetMapping("/admin/delete/{id}")
  public String deleteUser(@PathVariable("id") Long id, ModelMap model) {
    model.addAttribute("user", userService.getUser(id));
    return "main";
  }
  @DeleteMapping("/admin/delete")
  public String delete(Long id) {
    userService.removeUser(id);
    return "redirect:/admin";
  }

}
