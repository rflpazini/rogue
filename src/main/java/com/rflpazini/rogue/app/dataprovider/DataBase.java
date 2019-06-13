package com.rflpazini.rogue.app.dataprovider;

import com.rflpazini.rogue.app.dataprovider.model.Transfer;
import com.rflpazini.rogue.app.dataprovider.model.Customer;
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
