package com.rflpazini.rogue.usecase;

import com.rflpazini.rogue.dataprovider.impl.UserRepository;
import com.rflpazini.rogue.dataprovider.model.UserModel;
import java.util.List;
import javax.inject.Inject;

public class UserService {

  @Inject private UserRepository repository;

  public List<UserModel> getAll() {
    return repository.findAll();
  }

  public UserModel saveUser(UserModel user) {
    return repository.save(user);
  }
}
