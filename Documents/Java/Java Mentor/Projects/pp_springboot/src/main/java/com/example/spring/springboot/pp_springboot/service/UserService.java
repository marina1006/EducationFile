package com.example.spring.springboot.pp_springboot.service;

import com.example.spring.springboot.pp_springboot.model.User;
import com.example.spring.springboot.pp_springboot.repository.UserRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

  @Autowired
  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> listUsers() {

    return userRepository.findAll();

  }
  public User getUser(Long id) {

    return userRepository.getReferenceById(id);
  }
  public void saveUser(User user) {

    userRepository.save(user);
  }

  public void removeUser(Long id) {

    userRepository.deleteById(id);

  }

  public void update(Long id, User user) {
    user.setName(user.getName());
    user.setSurname(user.getSurname());
    user.setEmail(user.getEmail());
  }
}
