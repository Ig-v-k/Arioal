package com.example.arioal.arioal.repository;

import com.example.arioal.arioal.entities.User;

import java.util.List;
import java.util.Optional;

public interface CRUDDataRepository {
  User addUser(String name, String username, String email, String password, String group);

  List<User> allUsers();

  Optional<User> findUserByUsername(String username);
}
