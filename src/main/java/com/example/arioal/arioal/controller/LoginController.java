package com.example.arioal.arioal.controller;

import com.example.arioal.arioal.util.AuthenticationUtil;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class LoginController implements Serializable {
  @NotEmpty
//  @Size(min = 3, message = "Login musi zawierac wiecej niz 3 symboli")
  private String username;
  @NotEmpty
  private String password;

  private String status;

  @Inject
  FacesContext facesContext;
  @Inject
  AuthenticationUtil authenticate;

  public void execute() throws IOException {
	switch (processAuthentication()) {
	  case SEND_CONTINUE:
		facesContext.responseComplete();
		break;
	  case SEND_FAILURE:
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", null));
		status = "Dane sa nieprawidlowe, sprobuj ponownie";
		break;
	  case SUCCESS:
		getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/home.xhtml");
		break;
	}
  }

  private AuthenticationStatus processAuthentication() {
	return authenticate.authenticationStatus(getExternalContext(), username, password);
  }

  private ExternalContext getExternalContext() {
	return facesContext.getExternalContext();
  }

  public void setUsername(String username) {
	this.username = username;
  }

  public void setPassword(String password) {
	this.password = password;
  }

  public String getUsername() {
	return username;
  }

  public String getPassword() {
	return password;
  }

  public String getStatus() {
	return status;
  }

  public void setStatus(String status) {
	this.status = status;
  }
}
