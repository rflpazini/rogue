package com.rflpazini.rogue.app.dataprovider;

import com.rflpazini.rogue.app.entrypoint.model.Transfer;
import com.rflpazini.rogue.app.entrypoint.model.Customer;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.UUID;

public enum DataBase {
  INSTANCE;

  private final Hashtable<String, Customer> user;
  private final Hashtable<String, Transfer> transfer;

  private DataBase() {
    user = new Hashtable<>();
    transfer = new Hashtable<>();
    createNewCustomer();
  }

  public Hashtable user() {
    return user;
  }

  public Hashtable transfer() {
    return transfer;
  }

  private void createNewCustomer() {
    Customer firstCustomer = new Customer();
    UUID id = UUID.randomUUID();

    firstCustomer.setAccountId(id.toString());
    firstCustomer.setName("John Due");
    firstCustomer.setEmail("jd@email.com");
    firstCustomer.setCreatedAt(LocalDateTime.now());
    firstCustomer.setBalance(125);

    user.put(id.toString(), firstCustomer);
  }
}
