package com.rflpazini.rogue.dataprovider.impl;

import com.rflpazini.rogue.dataprovider.CrudPattern;
import com.rflpazini.rogue.dataprovider.DataBase;
import com.rflpazini.rogue.dataprovider.model.UserModel;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

public class UserRepository implements CrudPattern<UserModel> {

  public static final Hashtable<String, UserModel> USER = DataBase.INSTANCE.user();

  @Override
  public UserModel save(UserModel entity) {
    String id = UUID.randomUUID().toString();
    entity.setId(id);
    USER.put(id, entity);
    return USER.get(id);
  }

  @Override
  public UserModel update(UserModel entity) {
    return USER.put(entity.getId(), entity);
  }

  @Override
  public UserModel delete(UserModel entity) {
    return USER.remove(entity);
  }

  @Override
  public UserModel finbById(String id) {
    return USER.get(id);
  }

  @Override
  public List<UserModel> findAll() {
    return new ArrayList<>(USER.values());
  }
}
