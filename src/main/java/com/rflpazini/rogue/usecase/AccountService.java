package com.rflpazini.rogue.usecase;

import com.rflpazini.rogue.dataprovider.impl.CustomerRepository;
import com.rflpazini.rogue.dataprovider.model.Account;
import com.rflpazini.rogue.dataprovider.model.Customer;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

public class AccountService {

  @Inject CustomerRepository customerRepository;

  public Account getAccountInfo(String id) {
    Customer customerAccount = customerRepository.findById(id);

    return Account.builder()
        .balance(customerAccount.getBalance())
        .createdAt(customerAccount.getCreatedAt())
        .build();
  }

  public Account deposit(String id, double amount) {
    Customer customerAccount = customerRepository.findById(id);
    double newBalance = customerAccount.getBalance() + amount;

    customerAccount.setBalance(newBalance);
    customerRepository.update(customerAccount);

    return Account.builder().balance(newBalance).build();
  }

  public Account withdraw(String id, double amount) {
    Customer customerAccount = customerRepository.findById(id);
    if (customerAccount.getBalance() < amount) {
      throw new BadRequestException("You have insufficient founds");
    }

    double newBalance = customerAccount.getBalance() - amount;
    customerAccount.setBalance(newBalance);
    customerRepository.update(customerAccount);

    return Account.builder().balance(newBalance).build();
  }
}
