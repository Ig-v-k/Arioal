package com.example.arioal.arioal.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class DataInitializer {
  @Inject
  DataService dataService;

  public void execute(@Observes @Initialized(ApplicationScoped.class) Object event) {
	if (dataService.getAllUsers().isEmpty()) {
	  dataService.createUser("Vladimir Vysotskyi", "vladimir", "vladimir@email.com", "vladimirpass", "admin");
	  dataService.createUser("Igor Vysotskyi", "igor", "igor@email.com", "igorpass", "user");
	}
  }
}
