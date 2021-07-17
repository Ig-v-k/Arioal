package com.example.arioal.arioal.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements Validator<String> {

  /**
   * @see #validate(FacesContext, UIComponent, String) -
   * - From with the password field, validation method
   * @see #pattern - Regexp Java for password validation
   * ^                 # start-of-string
   * (?=.*[0-9])       # a digit must occur at least once
   * (?=.*[a-z])       # a lower case letter must occur at least once
   * (?=.*[A-Z])       # an upper case letter must occur at least once
   * (?=.*[@#$%^&+=])  # a special character must occur at least once
   * (?=\S+$)          # no whitespace allowed in the entire string
   * .{8,}             # anything, at least eight places though
   * $                 # end-of-string
   */
  private static final Pattern pattern =
		Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

  @Override
  public void validate(FacesContext facesContext, UIComponent uiComponent, String value) throws ValidatorException {
	Matcher matcher = pattern.matcher(value);
	if (!matcher.matches())
	  throw new ValidatorException(new FacesMessage(
			FacesMessage.SEVERITY_ERROR,
			this.getClass().getSimpleName() + ": Password Validation failed",
			"Invalid password."));
  }
}
