package com.example.spring.springboot.pp_springboot.controller;

import com.example.spring.springboot.pp_springboot.model.User;
import com.example.spring.springboot.pp_springboot.service.UserService;
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

@Controller
@RequestMapping("/users")
public class UserController {

  private final UserService service;

  @Autowired
  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping()
  public String showUsers(ModelMap model) {
    model.addAttribute("users", service.listUsers());
    return "users"; //view user of DAO
  }

  @GetMapping("/{id}")
  public String show(@PathVariable Long id, ModelMap model) {
    model.addAttribute("user", service.getUser(id));
    return "show"; //1 id user of DAO
  }
  @GetMapping("/new")
  public String newUsers(@ModelAttribute("user") User user) {

    return "new";
  }
  @GetMapping("/{id}/edit")
  public String edit(ModelMap model, @PathVariable("id") Long id) {
    model.addAttribute("user", service.getUser(id));
    return "edit";
  }
  @PostMapping()
  public String saveUsers(@ModelAttribute("user") User user) {
  service.saveUser(user);
    return "redirect:/users";
  }
  @PatchMapping("/{id}")//GetMapping
  public String updateUsers(@PathVariable("id") Long id, ModelMap model) {
    model.addAttribute("user", service.getUser(id));
    return "redirect:/users";
  }
  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") Long id) {
    service.removeUser(id);
    return "redirect:/users";
  }

}
