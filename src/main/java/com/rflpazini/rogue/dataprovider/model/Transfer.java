package com.rflpazini.rogue.dataprovider.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Transfer {

  private String id;

  private String originAccount;
  private String recipientAccount;
  private String description;
  private double amount;

  private LocalDateTime createdAt;
  private LocalDateTime processedAt;
}
