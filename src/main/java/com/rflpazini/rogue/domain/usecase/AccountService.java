package com.rflpazini.rogue.domain.usecase;

import com.rflpazini.rogue.app.entrypoint.model.Account;
import com.rflpazini.rogue.app.entrypoint.model.Customer;
import com.rflpazini.rogue.domain.dataprovider.CrudPattern;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

public class AccountService {

  @Inject CrudPattern<Customer> repository;

  public Account getAccountInfo(String id) {
    Customer customerAccount = repository.findById(id);

    return Account.builder()
        .balance(customerAccount.getBalance())
        .createdAt(customerAccount.getCreatedAt())
        .build();
  }

  public Account deposit(String id, double amount) {
    Customer customerAccount = repository.findById(id);
    double newBalance = customerAccount.getBalance() + amount;

    customerAccount.setBalance(newBalance);
    repository.update(customerAccount);

    return Account.builder().balance(newBalance).build();
  }

  public Account withdraw(String id, double amount) {
    Customer customerAccount = repository.findById(id);
    if (customerAccount.getBalance() < amount) {
      throw new BadRequestException("You have insufficient founds");
    }

    double newBalance = customerAccount.getBalance() - amount;
    customerAccount.setBalance(newBalance);
    repository.update(customerAccount);

    return Account.builder().balance(newBalance).build();
  }
}
