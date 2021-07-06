package com.example.arioal.arioal;

import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@WebFilter({"/*"})
public class SecurityFilter implements Filter {
  @Inject
  SecurityContext securityContext;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) servletRequest;
	HttpServletResponse response = (HttpServletResponse) servletResponse;
	Principal principalUser = securityContext.getCallerPrincipal();

	final String loginUrl = request.getContextPath() + "/login.xhtml?faces-redirect=true";

	if (request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) {
	  filterChain.doFilter(servletRequest, servletResponse);
	} else {
	  if (request.getServletPath().equals("/login.xhtml") || request.getServletPath().equals(loginUrl)) {
		filterChain.doFilter(servletRequest, servletResponse);
		return;
	  } else {
		if (principalUser == null) {
		  if ("partial/ajax".equals(request.getHeader("Faces-Request"))) {
			response.setContentType("text/xml");
			response.getWriter()
				  .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
				  .printf("<partial-response><redirect url=\"%s\"></redirect></partial-response>", loginUrl);
		  } else {
			response.sendRedirect(loginUrl);
		  }
		  return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
	  }
	}
  }
}
