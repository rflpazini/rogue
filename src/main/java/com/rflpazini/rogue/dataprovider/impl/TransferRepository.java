package com.rflpazini.rogue.dataprovider.impl;

import com.rflpazini.rogue.dataprovider.CrudPattern;
import com.rflpazini.rogue.dataprovider.DataBase;
import com.rflpazini.rogue.dataprovider.model.Transfer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

public class TransferRepository implements CrudPattern<Transfer> {

  public static final Hashtable<String, Transfer> TRANSFER = DataBase.INSTANCE.transfer();

  @Override
  public Transfer save(Transfer entity) {
    String id = UUID.randomUUID().toString();
    entity.setId(id);
    entity.setCreatedAt(LocalDateTime.now());

    TRANSFER.put(id, entity);

    System.out.println("save...");

    return TRANSFER.get(id);
  }

  @Override
  public Transfer update(Transfer entity) {
    return TRANSFER.replace(entity.getId(), entity);
  }

  @Override
  public Transfer delete(Transfer entity) {
    return null;
  }

  @Override
  public Transfer findById(String id) {
    return TRANSFER.get(id);
  }

  @Override
  public List<Transfer> findAll() {
    return new ArrayList<>(TRANSFER.values());
  }
}
