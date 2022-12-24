package web.controller;

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
import web.model.User;
import web.service.UserService;

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
    model.addAttribute("users", service.getListUsers());
    return "users"; //view user of DAO
  }

  @GetMapping("/{id}")
  public String show(@PathVariable long id, ModelMap model) {
    model.addAttribute("user", service.getUser(id));
    return "show"; //1 id user of DAO
  }
  @GetMapping("/new")
  public String newUsers(@ModelAttribute("user") User user) {

    return "new";
  }
  @GetMapping("/{id}/edit")
  public String edit(ModelMap model, @PathVariable("id") long id) {

    model.addAttribute("user", service.getUser(id));
    return "edit";
  }
  @PostMapping()
  public String saveUsers(@ModelAttribute("user") User user) {
  service.saveUser(user);
    return "redirect:/users";
  }
  @PatchMapping("/{id}")
  public String updateUsers(@ModelAttribute("user") User user, @PathVariable("id") long id) {
    service.update(id,user);
    return "redirect:/users";
  }
  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") long id) {
    service.removeUser(id);
    return "redirect:/users";
  }
}
