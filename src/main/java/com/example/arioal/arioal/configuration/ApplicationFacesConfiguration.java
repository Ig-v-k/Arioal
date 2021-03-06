package com.example.arioal.arioal.configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@DatabaseIdentityStoreDefinition(
	  dataSourceLookup = "jdbc/ArioalJDBCPostgresDS",
	  callerQuery = "select user_password from users where username = ?",
	  groupsQuery = "select user_group from users where username = ?"
)
@CustomFormAuthenticationMechanismDefinition(
	  loginToContinue = @LoginToContinue(
			loginPage = "/login.xhtml",
			errorPage = "",
			useForwardToLogin = false
	  )
)
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class ApplicationFacesConfiguration {
}
