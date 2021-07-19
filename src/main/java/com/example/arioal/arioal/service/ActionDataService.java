package com.example.arioal.arioal.service;

import com.example.arioal.arioal.entities.User;
import com.example.arioal.arioal.repository.ActionDataRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
public class ActionDataService {

  @EJB
  ActionDataRepository actionDataRepository;

  public User addUser(String name, String username, String email, String password, String group) {
	return actionDataRepository.addUser(name, username, email, password, group);
  }

  public List<User> allUsers() {
	return actionDataRepository.allUsers();
  }

  public Optional<User> findUserByUsername(String username) {
	return actionDataRepository.findUserByUsername(username);
  }
}
