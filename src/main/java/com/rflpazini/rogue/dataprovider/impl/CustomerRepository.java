package com.rflpazini.rogue.dataprovider.impl;

import com.rflpazini.rogue.dataprovider.CrudPattern;
import com.rflpazini.rogue.dataprovider.DataBase;
import com.rflpazini.rogue.dataprovider.model.Customer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

public class CustomerRepository implements CrudPattern<Customer> {

  public static final Hashtable<String, Customer> COSTUMER = DataBase.INSTANCE.user();

  @Override
  public Customer save(Customer entity) {
    String id = UUID.randomUUID().toString();
    entity.setAccountId(id);
    entity.setCreatedAt(LocalDateTime.now());

    COSTUMER.put(id, entity);

    return COSTUMER.get(id);
  }

  @Override
  public Customer update(Customer entity) {
    entity.setUpdatedAt(LocalDateTime.now());

    return COSTUMER.replace(entity.getAccountId(), entity);
  }

  @Override
  public Customer delete(Customer entity) {
    return COSTUMER.remove(entity);
  }

  @Override
  public Customer findById(String id) {
    return COSTUMER.get(id);
  }

  @Override
  public List<Customer> findAll() {
    return new ArrayList<>(COSTUMER.values());
  }
}
