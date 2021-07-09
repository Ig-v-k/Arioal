package com.example.arioal.arioal.repository;

import com.example.arioal.arioal.bean.Action;

import java.util.List;

public interface CRUDRepository {
  List<Action> getAll();
  List<Action> getAllBySize(final int size);
}
