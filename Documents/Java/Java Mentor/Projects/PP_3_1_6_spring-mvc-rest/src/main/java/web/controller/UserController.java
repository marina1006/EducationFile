package web.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import web.model.User;
import web.service.UserService;

@RestController
@RequestMapping("http://94.198.50.185:7081/api/users")
public class UserController {
  static RestTemplate restTemplate = new RestTemplate();
  private final UserService service;

  @Autowired
  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping()
  public List<User> showUsers() {

    return  service.getListUsers();
  }
  @PostMapping()
  public User addUser(@RequestBody User user){
    service.saveUser(user);
//    HttpHeaders headers = new HttpHeaders();
//    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//    HttpEntity<User> entity = new HttpEntity<User>(user,headers);
//
//    return restTemplate.exchange(
//        "http://94.198.50.185:7081/api/users", HttpMethod.POST, entity, String.class).getBody();
    return user;
  }
  @PutMapping()
  public User updateUsers(Long id, @RequestBody User user) {
    service.update(id,user);
//    HttpHeaders headers = new HttpHeaders();
//    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//    HttpEntity<User> entity = new HttpEntity<User>(user,headers);
//
//    return restTemplate.exchange(
//        "http://94.198.50.185:7081/api/users", HttpMethod.PUT, entity, String.class).getBody();
    return user;
  }
  @DeleteMapping("/{id}")
  public User delete(@RequestBody User user, Long id) {
     service.removeUser(id);
//    HttpHeaders headers = new HttpHeaders();
//    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//    HttpEntity<User> entity = new HttpEntity<User>(headers);
//
//    return restTemplate.exchange(
//        "http://94.198.50.185:7081/api/users"+id, HttpMethod.DELETE, entity, String.class).getBody();
return user;
  }

//
//  @GetMapping("/{id}")
//  public String show(@PathVariable Long id, ModelMap model) {
//    model.addAttribute("user", service.getUser(id));
//    return "show"; //1 id user
//  }
//  @GetMapping("/new")
//  public String newUsers(@ModelAttribute("user") User user) {
//
//    return "new";
//  }
//  @GetMapping("/{id}/edit")
//  public String edit(ModelMap model, @PathVariable("id") Long id) {
//    model.addAttribute("user", service.getUser(id));
//    return "edit";
//  }
//
//
//  public String saveUsers(@ModelAttribute("user") User user) {
//  service.saveUser(user);
//    return "redirect:/users";
//  }

}
