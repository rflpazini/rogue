package com.rflpazini.rogue.dataprovider;

import com.rflpazini.rogue.dataprovider.model.Customer;
import com.rflpazini.rogue.dataprovider.model.Transfer;
import java.util.Hashtable;
import java.util.UUID;

public enum DataBase {
  INSTANCE;

  private final Hashtable<UUID, Customer> user;
  private final Hashtable<UUID, Transfer> transfer;

  private DataBase() {
    user = new Hashtable<>();
    transfer = new Hashtable<>();
  }

  public Hashtable user() {
    return user;
  }

  public Hashtable transfer() {
    return transfer;
  }
}
