package com.example.arioal.arioal.controller;

import com.example.arioal.arioal.repository.DataRepository;
import com.example.arioal.arioal.service.ActionDataService;
import com.example.arioal.arioal.util.AuthenticationUtil;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class RegistrationController implements Serializable {
  @NotEmpty
  @Size(min = 3, message = "Login musi zawierac wiecej niz 3 symboli")
  private String username;
  @NotEmpty
  private String password;
  @NotEmpty
  private String email;
  private String name;
  private String status;

  @Inject
  FacesContext facesContext;

  @Inject
  DataRepository dataRepository;
/*
  @Inject
  ActionDataService actionDataService;
*/

  @Inject
  AuthenticationUtil authenticationUtil;

  public void execute() {
	try {
	  dataRepository.addUser(name, username, email, password, "user");
	  switch (authenticationUtil.authenticationStatus(facesContext.getExternalContext(), username, password)) {
		case SEND_CONTINUE:
		  facesContext.responseComplete();
		  break;
		case SEND_FAILURE:
		  facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", null));
		  status = "Dane sa nieprawidlowe, sprobuj ponownie";
		  break;
		case SUCCESS:
		  facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/home.xhtml");
		  break;
	  }
	} catch (IOException e) {
	  status = "Wprowadzone nieprawidlowe dane.";
	  e.printStackTrace();
	}
  }

  public String getUsername() {
	return username;
  }

  public void setUsername(String username) {
	this.username = username;
  }

  public String getPassword() {
	return password;
  }

  public void setPassword(String password) {
	this.password = password;
  }

  public String getEmail() {
	return email;
  }

  public void setEmail(String email) {
	this.email = email;
  }

  public String getStatus() {
	return status;
  }

  public void setStatus(String status) {
	this.status = status;
  }

  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }
}
