package com.example.arioal.arioal.service;

import com.example.arioal.arioal.bean.Action;
import com.example.arioal.arioal.bean.Category;
import com.example.arioal.arioal.repository.CRUDRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Named
@ApplicationScoped
public class ActionService implements CRUDRepository {

  private final List<Action> actionList = Arrays.asList(
		new Action(1, LocalDate.of(2021, 7, 18), "Novi Kalx S. C.", "biuro@pl.novikalx.com", "22 254 78 96", "aktywny", Category.SMS, "igor", "kontakt po ofercie", "potwierdzić zakres wykonywanych usług", true),
		new Action(2, LocalDate.of(2021, 8, 29), "Agencja Reklamowa Marketing S.A.", "info@arm.pr.com.pl", "58 324 64 95", "aktywny", Category.TELEPHONE, "vladimir", "zamówienie", "objaśnienie szczegółów oferty specjalnej", true),
		new Action(3, LocalDate.of(2021, 10, 2), "ProMax S.A.", "biuro@pl.promax.com", "71 658 78 90", "aktywny", Category.EMAIL, "vladimir", "oferta", "wyślij mu ofertę promocyjną", false),
		new Action(4, LocalDate.of(2021, 8, 29), "Agencja Reklamowa Marketing S.A.", "info@arm.pr.com.pl", "58 324 64 95", "aktywny", Category.TELEPHONE, "vladimir", "zamówienie", "objaśnienie szczegółów oferty specjalnej", true),
		new Action(5, LocalDate.of(2021, 8, 29), "Agencja Reklamowa Marketing S.A.", "info@arm.pr.com.pl", "58 324 64 95", "aktywny", Category.TELEPHONE, "vladimir", "zamówienie", "objaśnienie szczegółów oferty specjalnej", true),
		new Action(6, LocalDate.of(2021, 8, 29), "Agencja Reklamowa Marketing S.A.", "info@arm.pr.com.pl", "58 324 64 95", "aktywny", Category.TELEPHONE, "vladimir", "zamówienie", "objaśnienie szczegółów oferty specjalnej", true),
		new Action(7, LocalDate.of(2021, 8, 29), "Agencja Reklamowa Marketing S.A.", "info@arm.pr.com.pl", "58 324 64 95", "aktywny", Category.TELEPHONE, "vladimir", "zamówienie", "objaśnienie szczegółów oferty specjalnej", true),
		new Action(8, LocalDate.of(2021, 8, 29), "Agencja Reklamowa Marketing S.A.", "info@arm.pr.com.pl", "58 324 64 95", "aktywny", Category.TELEPHONE, "vladimir", "zamówienie", "objaśnienie szczegółów oferty specjalnej", true),
		new Action(9, LocalDate.of(2021, 10, 2), "ProMax S.A.", "biuro@pl.promax.com", "71 658 78 90", "aktywny", Category.EMAIL, "vladimir", "oferta", "wyślij mu ofertę promocyjną", false),
		new Action(10, LocalDate.of(2021, 10, 2), "ProMax S.A.", "biuro@pl.promax.com", "71 658 78 90", "aktywny", Category.EMAIL, "vladimir", "oferta", "wyślij mu ofertę promocyjną", false),
		new Action(11, LocalDate.of(2021, 10, 2), "ProMax S.A.", "biuro@pl.promax.com", "71 658 78 90", "aktywny", Category.EMAIL, "vladimir", "oferta", "wyślij mu ofertę promocyjną", false),
		new Action(12, LocalDate.of(2021, 10, 2), "ProMax S.A.", "biuro@pl.promax.com", "71 658 78 90", "aktywny", Category.EMAIL, "vladimir", "oferta", "wyślij mu ofertę promocyjną", false),
		new Action(13, LocalDate.of(2021, 10, 2), "ProMax S.A.", "biuro@pl.promax.com", "71 658 78 90", "aktywny", Category.EMAIL, "vladimir", "oferta", "wyślij mu ofertę promocyjną", false),
		new Action(14, LocalDate.of(2021, 7, 18), "Novi Kalx S. C.", "biuro@pl.novikalx.com", "22 254 78 96", "aktywny", Category.SMS, "igor", "kontakt po ofercie", "potwierdzić zakres wykonywanych usług", true),
		new Action(15, LocalDate.of(2021, 7, 18), "Novi Kalx S. C.", "biuro@pl.novikalx.com", "22 254 78 96", "aktywny", Category.SMS, "igor", "kontakt po ofercie", "potwierdzić zakres wykonywanych usług", true),
		new Action(16, LocalDate.of(2021, 7, 18), "Novi Kalx S. C.", "biuro@pl.novikalx.com", "22 254 78 96", "aktywny", Category.SMS, "igor", "kontakt po ofercie", "potwierdzić zakres wykonywanych usług", true),
		new Action(17, LocalDate.of(2021, 7, 18), "Novi Kalx S. C.", "biuro@pl.novikalx.com", "22 254 78 96", "aktywny", Category.SMS, "igor", "kontakt po ofercie", "potwierdzić zakres wykonywanych usług", true),
		new Action(18, LocalDate.of(2021, 7, 18), "Novi Kalx S. C.", "biuro@pl.novikalx.com", "22 254 78 96", "aktywny", Category.SMS, "igor", "kontakt po ofercie", "potwierdzić zakres wykonywanych usług", true),
		new Action(19, LocalDate.of(2021, 7, 18), "Novi Kalx S. C.", "biuro@pl.novikalx.com", "22 254 78 96", "aktywny", Category.SMS, "igor", "kontakt po ofercie", "potwierdzić zakres wykonywanych usług", true),
		new Action(20, LocalDate.of(2021, 7, 18), "Novi Kalx S. C.", "biuro@pl.novikalx.com", "22 254 78 96", "aktywny", Category.SMS, "igor", "kontakt po ofercie", "potwierdzić zakres wykonywanych usług", true)
  );

  @Override
  public List<Action> getAll() {
	return new ArrayList<>(actionList);
  }

  @Override
  public List<Action> getAllBySize(int size) {
	final int arraySize = actionList.size();
	if (size > arraySize) {
	  Random rand = new Random();
	  List<Action> randomList = new ArrayList<>();
	  for (int i = 0; i < size; i++) {
		int randomIndex = rand.nextInt(arraySize);
		final Action action = actionList.get(randomIndex);
		randomList.add(action);
	  }
	  return randomList;
	} else
	  return new ArrayList<>(actionList.subList(0, size));
  }
}
