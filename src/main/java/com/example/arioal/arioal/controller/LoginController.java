package com.example.arioal.arioal.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
  SecurityContext securityContext;

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
	ExternalContext ec = getExternalContext();
	return securityContext.authenticate((HttpServletRequest) ec.getRequest(),
		  (HttpServletResponse) ec.getResponse(),
		  AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(username, password)));
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
