package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

  @RequestMapping("/new")
  public String editUsers(@ModelAttribute("user") User user,ModelMap model) {

  model.addAttribute("user", user);
    return "edit";
  }
  @RequestMapping("/save")
  public String saveUsers(@ModelAttribute("user") User user) {
  service.saveUser(user);
    return "redirect:/";
  }
  @RequestMapping("/{id}/update")
  public String updateUsers(@ModelAttribute("user") User user,ModelMap model, @PathVariable long id) {

    model.addAttribute("user",service.getUser(id));
    return "edit";
  }
}
