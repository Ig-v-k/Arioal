package com.example.arioal.arioal.controller;

import com.example.arioal.arioal.entities.User;
import com.example.arioal.arioal.service.DataService;

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
import java.util.Optional;

@Named
@RequestScoped
public class HomeController implements Serializable {
  @Inject
  DataService dataService;

  @Inject
  SecurityContext securityContext;

  @Inject
  FacesContext facesContext;

  private Optional<User> currentUser;

  @PostConstruct
  public void initialize() {
	String username = securityContext.getCallerPrincipal().getName();
	this.currentUser = dataService.getUser(username);
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
