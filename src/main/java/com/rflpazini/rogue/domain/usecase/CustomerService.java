package com.rflpazini.rogue.domain.usecase;

import com.rflpazini.rogue.app.entrypoint.model.Customer;
import com.rflpazini.rogue.domain.dataprovider.CrudPattern;
import java.util.List;
import javax.inject.Inject;

public class CustomerService {

  @Inject private CrudPattern<Customer> repository;

  public List<Customer> getAll() {
    return repository.findAll();
  }

  public Customer findById(String id) {
    return repository.findById(id);
  }

  public Customer saveCustomer(Customer customer) {
    return repository.save(customer);
  }
}
