package com.rflpazini.rogue.dataprovider.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountModel {

  private double balance;
  private double credit;
}
