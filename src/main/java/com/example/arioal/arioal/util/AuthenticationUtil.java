package com.example.arioal.arioal.util;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApplicationScoped
public class AuthenticationUtil {

  @Inject
  private SecurityContext securityContext;

  public AuthenticationStatus authenticationStatus(ExternalContext context, String userName, String password) {
	return securityContext.authenticate(
		  (HttpServletRequest) context.getRequest(),
		  (HttpServletResponse) context.getResponse(),
		  AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(userName, password)));
  }
}
