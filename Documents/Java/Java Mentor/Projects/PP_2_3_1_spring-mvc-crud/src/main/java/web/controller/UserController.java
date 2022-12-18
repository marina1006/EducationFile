package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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

  @RequestMapping("/new")
  public String editUsers(ModelMap model) {
  User user = new User();
  model.addAttribute("user", user);
    return "edit";
  }
  @RequestMapping("/save")
  public String saveUsers(@ModelAttribute("user") User user) {
  service.saveUser(user);
    return "redirect:/";
  }
}
