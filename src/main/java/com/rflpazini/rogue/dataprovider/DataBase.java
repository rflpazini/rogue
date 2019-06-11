package com.rflpazini.rogue.dataprovider;

import java.util.Collection;
import java.util.Hashtable;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum DataBase {
  INSTANCE;

  private static final Logger LOGGER = LoggerFactory.getLogger(DataBase.class);

  private final Hashtable<UUID, String> db;

  private DataBase() {
    db = new Hashtable<>();
  }

  public final String getUserById(String enumType) {
    LOGGER.info("getUserById");
    return db.get(enumType);
  }

  public final Collection<String> getAllRegistries() {
    LOGGER.info("getAllRegistries");
    return db.values();
  }

  public final void add(String newData) {
    LOGGER.info("add newData");
    db.put(UUID.randomUUID(), newData);
  }
}
