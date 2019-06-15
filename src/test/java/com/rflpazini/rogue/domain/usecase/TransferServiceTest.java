package com.rflpazini.rogue.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import com.rflpazini.rogue.app.dataprovider.impl.TransferRepository;
import com.rflpazini.rogue.app.dataprovider.queue.TransferQueueProducer;
import com.rflpazini.rogue.app.entrypoint.model.Account;
import com.rflpazini.rogue.app.entrypoint.model.Transfer;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransferServiceTest {

  @Mock TransferRepository repository;

  @Mock AccountService accountService;

  @Mock TransferQueueProducer transferQueueProducer;

  @InjectMocks TransferService transferService;

  @Test
  public void testCreateNewTransfer() {
    // given
    Transfer mockedTransfer = new Transfer();
    mockedTransfer.setOriginAccount("1234");
    mockedTransfer.setRecipientAccount("4567");
    mockedTransfer.setAmount(932);

    Account mockedAccount = Account.builder().balance(123).createdAt(createMockedDate()).build();

    when(accountService.withdraw(anyString(), anyDouble())).thenReturn(mockedAccount);
    when(repository.save(any(Transfer.class))).thenReturn(mockedTransfer);

    // when
    Transfer actual = transferService.createTransfer(mockedTransfer);

    // then
    assertNotNull(actual);
    assertNotNull(actual.getCreatedAt());

    assertEquals(mockedTransfer.getOriginAccount(), actual.getOriginAccount());
    assertEquals(mockedTransfer.getRecipientAccount(), actual.getRecipientAccount());
  }

  @Test
  public void testUpdateTransfer() {
    // given
    Transfer mockedTransfer = new Transfer();
    mockedTransfer.setOriginAccount("1234");
    mockedTransfer.setRecipientAccount("4567");
    mockedTransfer.setAmount(932);

    Account mockedAccount = Account.builder().balance(123).createdAt(createMockedDate()).build();

    when(accountService.deposit(anyString(), anyDouble())).thenReturn(mockedAccount);
    when(repository.update(any(Transfer.class))).thenReturn(mockedTransfer);

    // when
    Transfer actual = transferService.updateTransfer(mockedTransfer);

    //then
    assertNotNull(actual);
    assertNotNull(actual.getProcessedAt());

    assertEquals(mockedTransfer.getOriginAccount(), actual.getOriginAccount());
    assertEquals(mockedTransfer.getRecipientAccount(), actual.getRecipientAccount());
  }

  private LocalDateTime createMockedDate() {
    Instant instant = Instant.parse("2019-06-13T16:02:42.00Z");
    ZoneId zoneId = ZoneId.systemDefault();
    Clock clock = Clock.fixed(instant, zoneId);

    return LocalDateTime.now(clock);
  }
}
