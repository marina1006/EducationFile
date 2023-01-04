package ru.kata.spring.boot_security.demo.model;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "surname")
  private String surname;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;
  @ManyToMany(fetch = FetchType.LAZY)
  private Set<Role> roles;

  public User() {

  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(String username, String password,
      Set<Role> roles) {
    this.username = username;
    this.password = password;
    this.setRoles(roles);
  }

  public User(Long id, String name, String surname, String email,
      String password, Set<Role> roles) {
    this.id = id;
    this.username = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return
        username + surname + email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(
        Collectors.toList());
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public User getUser() {
    return User.this;
  } // before fix config

}

