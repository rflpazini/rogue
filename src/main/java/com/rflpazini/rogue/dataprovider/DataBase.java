package com.rflpazini.rogue.dataprovider;

import com.rflpazini.rogue.dataprovider.model.*;
import java.util.Hashtable;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum DataBase {
  INSTANCE;

  private static final Logger LOGGER = LoggerFactory.getLogger(DataBase.class);

  private final Hashtable<UUID, UserModel> user;

  private DataBase() {
    user = new Hashtable<>();
  }

  public Hashtable user(){
    return user;
  }
}
