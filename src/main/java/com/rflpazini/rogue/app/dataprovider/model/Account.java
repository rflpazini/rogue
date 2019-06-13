package com.rflpazini.rogue.app.dataprovider.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {

  private double balance;
  private LocalDateTime createdAt;
}
