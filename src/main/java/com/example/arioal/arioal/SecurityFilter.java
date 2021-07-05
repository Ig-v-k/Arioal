package com.example.arioal.arioal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.ResourceHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

public class SecurityFilter implements Filter {

  private final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

  @Inject
  SecurityContext securityContext;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
	System.out.println("ARIOAL-SOUT: filter init");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) servletRequest;
	HttpServletResponse response = (HttpServletResponse) servletResponse;
	Principal principalUser = securityContext.getCallerPrincipal();

	final String loginUrl = request.getContextPath() + "/login.xhtml?faces-redirect=true";

	if (request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) {
	  filterChain.doFilter(servletRequest, servletResponse);
	}

	if (request.getServletPath().equals("/login.xhtml")) {
	  filterChain.doFilter(servletRequest, servletResponse);
	  return;
	} else {
	  if (principalUser == null) {
		if ("partial/ajax".equals(request.getHeader("Faces-Request"))) {
		  System.out.println("ARIOAL-SOUT: ajax");
		  response.setContentType("text/xml");
		  response.getWriter()
				.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
				.printf("<partial-response><redirect url=\"%s\"></redirect></partial-response>", loginUrl);
		} else {
		  System.out.println("ARIOAL-SOUT: default");
		  response.sendRedirect(loginUrl);
		}
		return;
	  }
	}

	filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {
	System.out.println("ARIOAL-SOUT: filter destroy");
  }
}
