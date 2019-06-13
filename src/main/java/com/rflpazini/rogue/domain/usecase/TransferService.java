package com.rflpazini.rogue.domain.usecase;

import com.rflpazini.rogue.app.dataprovider.impl.TransferRepository;
import com.rflpazini.rogue.app.dataprovider.model.Transfer;
import com.rflpazini.rogue.app.dataprovider.queue.TransferQueueProducer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;

public class TransferService {

  @Inject AccountService accountService;
  @Inject TransferRepository repository;

  @Inject TransferQueueProducer transferQueueProducer;

  public Transfer createTransfer(Transfer entity) {
    accountService.withdraw(entity.getOriginAccount(), entity.getAmount());

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
