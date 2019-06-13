package com.rflpazini.rogue.dataprovider;

import java.util.List;

public interface CrudPattern<T> {
  T save(T entity);

  T update(T entity);

  T delete(T entity);

  T findById(String id);

  List<T> findAll();
}
