package web.controller;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import web.model.User;
import web.service.UserService;

@RestController

public class UserController {
  static RestTemplate restTemplate = new RestTemplate();
  private final UserService service;

  @Autowired
  public UserController(UserService service) {
    this.service = service;
  }

  @RequestMapping(value = "http://94.198.50.185:7081/api/users")
  public String showUsers() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity <String> entity = new HttpEntity<String>(headers);

    return restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.GET, entity, String.class).getBody();
//    return  service.getListUsers();
  }
  @RequestMapping(value = "http://94.198.50.185:7081/api/users", method = RequestMethod.POST)
  public String addUser(@RequestBody User user){
    user.setId(3L);
    user.setAge((byte) 23);
    user.setName("James");
    user.setLastName("Brown");

//    service.saveUser(user);

//    URI path = ServletUriComponentsBuilder.fromCurrentRequest()
//        .path("/{id}")
//        .buildAndExpand(user.getId())
//        .toUri();
//
//    return ResponseEntity.created(path).build();
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<User> entity = new HttpEntity<User>(user,headers);

    return restTemplate.exchange(
        "http://94.198.50.185:7081/api/users", HttpMethod.POST, entity, String.class).getBody();
//    return user;
  }
  @RequestMapping(value = "http://94.198.50.185:7081/api/user", method = RequestMethod.PUT)
  public String updateUsers(Long id, @RequestBody User user) {
    user.setId(3L);
    user.setName("Thomas");
    user.setLastName("");
    user.setAge((byte) 23);

//    service.update(id,user);
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<User> entity = new HttpEntity<User>(user,headers);

    return restTemplate.exchange(
        "http://94.198.50.185:7081/api/users", HttpMethod.PUT, entity, String.class).getBody();
//    return user;
  }
  @RequestMapping(value = "http://94.198.50.185:7081/api/user/3", method = RequestMethod.DELETE)
  public String delete(@PathVariable("id") Long id) {
//     service.removeUser(id);
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<User> entity = new HttpEntity<User>(headers);

    return restTemplate.exchange(
        "http://94.198.50.185:7081/api/users"+id, HttpMethod.DELETE, entity, String.class).getBody();
//return user;
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
