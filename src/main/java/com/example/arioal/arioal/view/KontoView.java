package com.example.arioal.arioal.view;

import com.example.arioal.arioal.entities.User;
import com.example.arioal.arioal.repository.DataRepository;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import java.io.Serializable;
import java.util.Optional;

@Named
@ViewScoped
public class KontoView implements Serializable {
  @Inject
  DataRepository dataRepository;
  /*
	@Inject
	ActionDataRepository actionDataRepository;
  */
//  private UploadedFile uploadedFile;
//  private StreamedContent streamedContent;
  @Inject
  SecurityContext securityContext;
  @Inject
  FacesContext facesContext;
  private Optional<User> user;

  @PostConstruct
  public void init() {
	String username = securityContext.getCallerPrincipal().getName();
	user = dataRepository.findUserByUsername(username);
  }

  public User getUser() {
	return user.orElse(null);
  }

  public boolean isAdmin() {
	return securityContext.isCallerInRole("admin");
  }

/*
  public UploadedFile getUploadedFile() {
	return uploadedFile;
  }

  public void setUploadedFile(UploadedFile uploadedFile) {
	this.uploadedFile = uploadedFile;
  }

  public void handleFileUpload() {
	if (facesContext.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
	  streamedContent = new DefaultStreamedContent();
	else
	  streamedContent = DefaultStreamedContent.builder()
			.name(uploadedFile.getFileName())
			.stream(() -> new ByteArrayInputStream(getUploadedFile().getContent()))
			.build();
  }

  public StreamedContent getStreamedContent() {
    if (streamedContent == null || streamedContent.getStream() == null)
    return streamedContent;
  }
*/
}
