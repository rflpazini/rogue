package com.rflpazini.rogue.usecase;

import com.rflpazini.rogue.dataprovider.impl.CustomerRepository;
import com.rflpazini.rogue.dataprovider.model.Customer;
import java.util.List;
import javax.inject.Inject;

public class CustomerService {

  @Inject private CustomerRepository repository;

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
