package com.example.arioal.arioal.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class Action implements Serializable {
  private Integer id;
  private LocalDate date;
  private String contactName;
  private String contactEmail;
  private String contactFax;
  private String contactStatus;
  private Category category;
  private String performerName;
  private String goalName;
  private String describeName;
  private boolean actionStatus;

  public Action(int id, LocalDate date, String contactName, String contactEmail, String contactFax, String contactStatus, Category category, String performerName, String goalName, String describeName, boolean actionStatus) {
	this.id = id;
	this.date = date;
	this.contactName = contactName;
	this.contactEmail = contactEmail;
	this.contactFax = contactFax;
	this.contactStatus = contactStatus;
	this.category = category;
	this.performerName = performerName;
	this.goalName = goalName;
	this.describeName = describeName;
	this.actionStatus = actionStatus;
  }

  public Action() {

  }

  public Integer getId() {
	return id;
  }

  public void setId(Integer id) {
	this.id = id;
  }

  public LocalDate getDate() {
	return date;
  }

  public void setDate(LocalDate date) {
	this.date = date;
  }

  public String getContactName() {
	return contactName;
  }

  public void setContactName(String contactName) {
	this.contactName = contactName;
  }

  public String getContactEmail() {
	return contactEmail;
  }

  public void setContactEmail(String contactEmail) {
	this.contactEmail = contactEmail;
  }

  public String getContactFax() {
	return contactFax;
  }

  public void setContactFax(String contactFax) {
	this.contactFax = contactFax;
  }

  public String getContactStatus() {
	return contactStatus;
  }

  public void setContactStatus(String contactStatus) {
	this.contactStatus = contactStatus;
  }

  public Category getCategory() {
	return category;
  }

  public void setCategory(Category category) {
	this.category = category;
  }

  public String getPerformerName() {
	return performerName;
  }

  public void setPerformerName(String performerName) {
	this.performerName = performerName;
  }

  public String getGoalName() {
	return goalName;
  }

  public void setGoalName(String goalName) {
	this.goalName = goalName;
  }

  public String getDescribeName() {
	return describeName;
  }

  public void setDescribeName(String describeName) {
	this.describeName = describeName;
  }

  public boolean isActionStatus() {
	return actionStatus;
  }

  public void setActionStatus(boolean actionStatus) {
	this.actionStatus = actionStatus;
  }
}
