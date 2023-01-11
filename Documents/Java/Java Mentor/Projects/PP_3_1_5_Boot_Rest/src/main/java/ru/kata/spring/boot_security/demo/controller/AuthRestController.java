package ru.kata.spring.boot_security.demo.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class AuthRestController {

  private final UserService userService;
  private final RoleService roleService;

  @Autowired
  public AuthRestController(UserService userService,
      RoleService roleService) {
    this.userService = userService;

    this.roleService = roleService;
  }
  @GetMapping("/admin")
  public ResponseEntity<List<User>> showUsers() {

    List<User> allUsers = userService.listUsers();

    return ResponseEntity.ok(allUsers);
  }
  @GetMapping("/admin/{id}")
  public ResponseEntity<User> showAll(@PathVariable Long id) {
  User user = userService.getUser(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping("/admin/new")
  public ResponseEntity<User> saveUsers(@RequestBody User user) {

    userService.saveUser(user);
    return ResponseEntity.ok(user);
  }

  @PatchMapping("/admin/update/{id}")
  public ResponseEntity<User> updateUsers(@RequestBody User user) {
    userService.saveUser(user);
    return ResponseEntity.ok(user);
  }

  @DeleteMapping("/admin/delete/{id}")
  public ResponseEntity<User> delete(@PathVariable Long id) {
    userService.removeUser(id);
    return ResponseEntity.ok().build();
  }

}
