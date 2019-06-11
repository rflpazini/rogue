package com.rflpazini.rogue.entrypoint.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {

  private String id;
  private String name;
  private String email;
}
