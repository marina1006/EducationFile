package ru.kata.spring.boot_security.demo.controller;

import java.security.Principal;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
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

  @PostMapping("/")
  public String saveUsers(@ModelAttribute("user") User user) {

    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    user.setRoles((Set<Role>) roleService.getRole(1L));
    userService.saveUser(user);
    return "redirect:/admin";
  }

  @PatchMapping("/{id}")
  public String updateUsers(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    userService.update(id,user);
    return "redirect:/admin";
  }

  @DeleteMapping("/admin/{id}")
  public String delete(@PathVariable("id") Long id) {
    userService.removeUser(id);
    return "redirect:/admin";
  }

  @GetMapping("/user")
  public String userPage(Principal principal, ModelMap model) {
    model.addAttribute("user", userService.findByUsername(principal.getName()));
    model.addAttribute("simpleGrantedAuthority", new SimpleGrantedAuthority("ADMIN"));
    return "user";
  }
}
