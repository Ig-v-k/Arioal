package com.example.arioal.arioal.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator<String> {
  private static final Pattern pattern =
		Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  @Override
  public void validate(FacesContext facesContext, UIComponent uiComponent, String s) throws ValidatorException {
	Matcher matcher = pattern.matcher(s);
	if (!matcher.matches())
	  throw new ValidatorException(new FacesMessage(
			FacesMessage.SEVERITY_ERROR,
			this.getClass().getSimpleName() + ": Email Validation failed",
			"Email podany nieprawidlowo."));
  }
}
