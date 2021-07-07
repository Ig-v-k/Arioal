package com.example.arioal.arioal.view;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

@Named
@ViewScoped
public class CalendarView implements Serializable {

  private Date date;

  @PostConstruct
  public void init() {
	date = GregorianCalendar.getInstance().getTime();
  }

  public Date getDate() {
	return date;
  }

  public void setDate(Date date) {
	this.date = date;
  }
}
