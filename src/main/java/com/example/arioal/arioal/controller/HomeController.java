package com.example.arioal.arioal.controller;

import com.example.arioal.arioal.entities.User;
import com.example.arioal.arioal.repository.DataRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.Principal;
import java.util.Optional;

@Named
@RequestScoped
public class HomeController implements Serializable {
  @Inject
  DataRepository dataRepository;

  @Inject
  SecurityContext securityContext;

  @Inject
  FacesContext facesContext;

  private Optional<User> currentUser;

  @PostConstruct
  public void initialize() {
	Principal principal = securityContext.getCallerPrincipal();
	String username = (principal == null) ? "Anonymous" : principal.getName();
	this.currentUser = dataRepository.findUserByUsername(username);
  }

  public User getCurrentUser() {
	return currentUser.orElse(null);
  }

  public boolean isAllowedToSeeUsers() {
	return securityContext.isCallerInRole("admin");
  }

  public String logout() throws ServletException {
	ExternalContext ec = facesContext.getExternalContext();
	((HttpServletRequest) ec.getRequest()).logout();
	return "/login.xhtml?faces-redirect=true";
  }
}
