package com.rflpazini.rogue.domain.dataprovider;

public interface QueueProvider<T> {
  void post(T entity);
}
