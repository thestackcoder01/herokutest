package com.example.demo.repositary;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;
import java.util.Optional;

public interface UserRepositary extends JpaRepository<User, Integer>{
 Optional<User> findByEmail(String s);
}
