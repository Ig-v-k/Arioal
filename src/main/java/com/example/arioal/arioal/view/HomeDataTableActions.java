package com.example.arioal.arioal.view;

import com.example.arioal.arioal.bean.Action;
import com.example.arioal.arioal.bean.Category;
import com.example.arioal.arioal.service.ActionService;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Named("dtActionBean")
@ViewScoped
public class HomeDataTableActions implements Serializable {
  private List<Action> actionList;
  private List<Action> selectedActionList;
  private List<Action> filteredActionList;
  private Action selectedAction;
  private String actionStatus = "false";

  @Inject
  private ActionService actionService;

  public HomeDataTableActions() {
  }

  @PostConstruct
  public void init() {
	actionList = actionService.getAllBySize(15);
  }

  public void goalInit(final String goal) {
    selectedAction.setGoalName(goal);
  }

  public void dateInit(SelectEvent<LocalDate> event) {
    selectedAction.setDate(event.getObject());
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
	  selectedAction.setActionStatus(Boolean.parseBoolean(actionStatus));
	  actionList.add(selectedAction);
	  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Action Added"));
	} else {
	  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Action Updated"));
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

  public String getActionStatus() {
	return actionStatus;
  }

  public void setActionStatus(String actionStatus) {
	this.actionStatus = actionStatus;
  }

  public List<Action> getFilteredActionList() {
	return filteredActionList;
  }

  public void setFilteredActionList(List<Action> filteredActionList) {
	this.filteredActionList = filteredActionList;
  }
}
