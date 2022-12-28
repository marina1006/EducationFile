package com.example.spring.springboot.pp_springboot.repository;


import com.example.spring.springboot.pp_springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
