package com.rflpazini.rogue.app.dataprovider.impl;

import com.rflpazini.rogue.app.dataprovider.DataBase;
import com.rflpazini.rogue.app.entrypoint.model.Customer;
import com.rflpazini.rogue.domain.dataprovider.CrudPattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.NotFoundException;

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
    Customer customer = COSTUMER.get(entity.getAccountId());
    checkIfCustomerExist(customer);

    entity.setUpdatedAt(LocalDateTime.now());

    return COSTUMER.replace(entity.getAccountId(), entity);
  }

  @Override
  public Customer delete(final String id) {
    Customer customer = COSTUMER.get(id);
    checkIfCustomerExist(customer);

    return COSTUMER.remove(id);
  }

  @Override
  public Customer findById(String id) {
    Customer customer = COSTUMER.get(id);
    checkIfCustomerExist(customer);

    return customer;
  }

  @Override
  public List<Customer> findAll() {
    return new ArrayList<>(COSTUMER.values());
  }

  private void checkIfCustomerExist(Customer customer) {
    if (customer == null) {
      throw new NotFoundException("Customer not found");
    }
  }
}
