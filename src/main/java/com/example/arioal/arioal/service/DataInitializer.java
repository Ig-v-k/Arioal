package com.example.arioal.arioal.service;

import com.example.arioal.arioal.repository.DataRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class DataInitializer {
  @Inject
  DataRepository dataRepository;

  public void execute(@Observes @Initialized(ApplicationScoped.class) Object event) {
	if (dataRepository.allUsers().isEmpty()) {
	  dataRepository.addUser("Vladimir Vysotskyi", "vladimir", "vladimir@email.com", "vladimirpass", "admin");
	  dataRepository.addUser("Igor Vysotskyi", "igor", "igor@email.com", "igorpass", "user");
	}
  }
}
