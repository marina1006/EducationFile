package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

  private UserService service;

  @Autowired
  public UserController(UserService service) {
    this.service = service;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String showUsers(ModelMap model) {
    model.addAttribute("users", service.getListUsers());
    return "users"; //view
  }

  @GetMapping("/{id}")
  public String show(ModelMap model, @PathVariable long id) {
    model.addAttribute("user", service.getUser(id));
    return "show"; //view
  }
  @GetMapping("/new")
  public String newUsers(@ModelAttribute("user") User user) {

    return "new";
  }
  @GetMapping("/{id}/edit")
  public String edit(@ModelAttribute("user") User user,ModelMap model) {

    model.addAttribute("user", user);
    return "edit";
  }
  @PostMapping()
  public String saveUsers(@ModelAttribute("user") User user) {
  service.saveUser(user);
    return "redirect:/";
  }
  @PatchMapping("/{id}")
  public String updateUsers(@ModelAttribute("user") User user, @PathVariable long id) {

    //model.addAttribute("user",service.getUser(id));
    service.update(id,user);
    return "redirect:/";
  }
  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    service.removeUser(id);
    return "redirect:/users";
  }
}
