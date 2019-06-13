package com.rflpazini.rogue.dataprovider.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Customer {

  private String accountId;

  private String name;
  private String email;
  private double balance;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
