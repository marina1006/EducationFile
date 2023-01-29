package ru.kata.spring.boot_security.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
@RequestMapping("/")
public class AuthRestController {

  private final UserService userService;
  private final RoleService roleService;

  public AuthRestController(UserService userService,
      RoleService roleService) {
    this.userService = userService;

    this.roleService = roleService;
  }

  @GetMapping("admin/users")
  public ResponseEntity<List<User>> showUsers() {

    List<User> allUsers = userService.listUsers();

    return ResponseEntity.ok(allUsers);
  }

  @GetMapping("admin/users/{id}")
  public ResponseEntity<User> show(@PathVariable("id") Long id) {
    User user = userService.getUser(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping("admin/users")
  public ResponseEntity<User> saveUsers(@RequestBody User user) {

    userService.saveUser(user);
    return ResponseEntity.ok(user);
  }

  @PatchMapping("admin/users/{id}")
  public ResponseEntity<User> updateUsers(@RequestBody User user, @PathVariable("id") Long id) {
    userService.update(user,id);
    return ResponseEntity.ok(user);
  }

  @DeleteMapping("admin/users/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    userService.removeUser(id);
    return ResponseEntity.ok().build();
  }
  @GetMapping("user/userAuth")
  public ResponseEntity<User> show(@AuthenticationPrincipal User user) {

    return ResponseEntity.ok(user);
  }

}
