package com.example.arioal.arioal.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

  public void execute() {

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
}
