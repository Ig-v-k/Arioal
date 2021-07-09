package com.example.arioal.arioal.view;

import com.example.arioal.arioal.bean.Action;
import com.example.arioal.arioal.service.ActionService;

import javax.annotation.PostConstruct;
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
    actionList = actionService.getAll();
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
