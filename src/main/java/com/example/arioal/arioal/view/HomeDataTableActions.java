package com.example.arioal.arioal.view;

import com.example.arioal.arioal.bean.Action;
import com.example.arioal.arioal.bean.Category;
import com.example.arioal.arioal.service.ActionService;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named("dtActionBean")
@ViewScoped
public class HomeDataTableActions implements Serializable {
  private List<Action> actionList;
  private List<Action> selectedActionList;
  private Action selectedAction;

  @Inject
  private ActionService actionService;

  @PostConstruct
  public void init() {
	actionList = actionService.getAllBySize(3);
  }

  public void categoryItem(final String category) {
	selectedAction.setCategory(Category.valueOf(category));
  }

  public void performerItem(final String performerName) {
	selectedAction.setPerformerName(performerName);
  }

  public void contactStatus(final String status) {
	selectedAction.setContactStatus(status);
  }

  public void saveAction() {
	if (selectedAction.getId() == null) {
	  final int idLastElem = actionList.get(actionList.size() - 1).getId();
	  selectedAction.setId(idLastElem + 1);
	  actionList.add(selectedAction);
	  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
	} else {
	  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
	}

	PrimeFaces.current().executeScript("PF('dlg2').hide()");
	PrimeFaces.current().ajax().update("tabsAction:formAction:home_act_messages", "tabsAction:formAction:dataTableActions");
  }

  public void openNew() {
	selectedAction = new Action();
  }

  public void deleteSelectedActionList() {
	actionList.removeAll(selectedActionList);
	selectedActionList = Collections.emptyList();
  }

  public List<Action> getActionList() {
	return actionList;
  }

  public void setActionList(List<Action> actionList) {
	this.actionList = actionList;
  }

  public List<Action> getSelectedActionList() {
	return selectedActionList;
  }

  public void setSelectedActionList(List<Action> selectedActionList) {
	this.selectedActionList = selectedActionList;
  }

  public Action getSelectedAction() {
	return selectedAction;
  }

  public void setSelectedAction(Action selectedAction) {
	this.selectedAction = selectedAction;
  }
}
