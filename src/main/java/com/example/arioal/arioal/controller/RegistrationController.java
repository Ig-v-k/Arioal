package com.example.arioal.arioal.controller;

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
  private String status;

  @Inject
  FacesContext facesContext;

  public void execute() {
	try {
	  facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/home.xhtml");
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
}
