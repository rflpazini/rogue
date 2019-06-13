package com.rflpazini.rogue.usecase;

import com.rflpazini.rogue.config.TransferQueueProducer;
import com.rflpazini.rogue.dataprovider.impl.TransferRepository;
import com.rflpazini.rogue.dataprovider.model.Transfer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;

public class TransferService {

  @Inject AccountService accountService;
  @Inject TransferRepository repository;

  @Inject TransferQueueProducer transferQueueProducer;

  public Transfer createTransfer(String id, Transfer entity) {
    entity.setOriginAccount(id);

    accountService.withdraw(id, entity.getAmount());

    return postToQueue(entity);
  }

  public List<Transfer> getAllTransfers() {
    return repository.findAll();
  }

  public Transfer getTransferById(String id) {
    return repository.findById(id);
  }

  public Transfer updateTransfer(Transfer entity) {
    entity.setProcessedAt(LocalDateTime.now());

    accountService.deposit(entity.getRecipientAccount(), entity.getAmount());

    return repository.update(entity);
  }

  private Transfer postToQueue(final Transfer entity) {
    Transfer newTransfer = entity;
    newTransfer.setId(UUID.randomUUID().toString());
    newTransfer.setCreatedAt(LocalDateTime.now());

    transferQueueProducer.post(entity);

    return repository.save(entity);
  }

  private void persistNewEntry(final Transfer entity) {
    repository.save(entity);
  }
}
