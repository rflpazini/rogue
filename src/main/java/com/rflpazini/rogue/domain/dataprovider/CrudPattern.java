package com.rflpazini.rogue.domain.dataprovider;

import java.util.List;

public interface CrudPattern<T> {
  T save(T entity);

  T update(T entity);

  T delete(String id);

  T findById(String id);

  List<T> findAll();
}
