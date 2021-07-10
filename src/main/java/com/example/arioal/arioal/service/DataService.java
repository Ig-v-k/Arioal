package com.example.arioal.arioal.service;

import com.example.arioal.arioal.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DataService {

  //  @PersistenceContext(unitName = "ArioalJDBC")
  private static EntityManager entityManager;

  @Inject
  Pbkdf2PasswordHash pbkdf2PasswordHash;

  static {
	try {
	  final String jdbcConfServerPool = "ArioalJDBC";
	  final String profileStage = FacesContext.getCurrentInstance().getApplication().getProjectStage().toString();
	  final String herokuJDBCConfig =
			FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "cfg").getString("jdbcurl");
	  entityManager =
			Persistence.createEntityManagerFactory(
				  profileStage.equalsIgnoreCase("production") ? herokuJDBCConfig : jdbcConfServerPool
			).createEntityManager();
	} catch (Throwable ex) {
	  throw new ExceptionInInitializerError(ex);
	}
  }

  @Transactional
  public User createUser(String name, String username, String password, String group) {
	User newUser = new User(name, username, pbkdf2PasswordHash.generate(password.toCharArray()), group);
	entityManager.joinTransaction();
	entityManager.persist(newUser);
	entityManager.flush();
	return newUser;
  }

  public List<User> getAllUsers() {
	return entityManager.createNamedQuery("User.all", User.class).getResultList();
  }

  public Optional<User> getUser(String username) {
	return entityManager.createNamedQuery("User.byUsername", User.class)
		  .setParameter("username", username)
		  .getResultList()
		  .stream()
		  .findFirst();
  }
}
