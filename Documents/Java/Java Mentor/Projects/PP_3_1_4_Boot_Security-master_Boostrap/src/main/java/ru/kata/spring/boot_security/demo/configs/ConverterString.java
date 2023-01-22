package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import ru.kata.spring.boot_security.demo.model.Role;
@Configuration
public  class ConverterString implements Converter<String, Role> {

  @Override
  public Role convert(String id) {
    Role role = new Role();
    role.setId(Long.valueOf(id));
    return role;
  }

}
