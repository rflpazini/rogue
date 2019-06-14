package com.rflpazini.rogue.app.dataprovider.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.rflpazini.rogue.app.dataprovider.DataBase;
import com.rflpazini.rogue.app.entrypoint.model.Transfer;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TransferRepositoryTest {

  private TransferRepository transferRepository;

  @Before
  public void setup() {
    this.transferRepository = new TransferRepository();
  }

  @After
  public void clear() {
    DataBase.INSTANCE.transfer().clear();
  }

  @Test
  public void testCreateNewTransfer() {
    // given
    String mockedOriginAccount = "234-432";
    String mockedRecipientAccount = "543-433";
    String mockedDescription = "mocked text";
    double mockedAmount = 123.0;

    Transfer mockedTransfer = new Transfer();
    mockedTransfer.setOriginAccount(mockedOriginAccount);
    mockedTransfer.setRecipientAccount(mockedRecipientAccount);
    mockedTransfer.setDescription(mockedDescription);
    mockedTransfer.setAmount(mockedAmount);

    // when
    Transfer actual = transferRepository.save(mockedTransfer);

    // then
    assertEquals(mockedTransfer.getOriginAccount(), actual.getOriginAccount());
    assertEquals(mockedTransfer.getRecipientAccount(), actual.getRecipientAccount());
    assertEquals(mockedTransfer.getAmount(), actual.getAmount(), 0.001);

    assertNotNull(actual.getCreatedAt());
  }

  @Test
  public void testUpdateTransfer() {
    // given
    String mockedOriginAccount = "234-432";
    String mockedRecipientAccount = "543-433";
    String mockedDescription = "mocked text";
    double mockedAmount = 123.0;

    Transfer mockedTransfer = new Transfer();
    mockedTransfer.setOriginAccount(mockedOriginAccount);
    mockedTransfer.setRecipientAccount(mockedRecipientAccount);
    mockedTransfer.setDescription(mockedDescription);
    mockedTransfer.setAmount(mockedAmount);

    mockedTransfer = transferRepository.save(mockedTransfer);

    // when
    mockedTransfer.setDescription("new text");
    Transfer actual = transferRepository.update(mockedTransfer);

    // then
    assertEquals("new text", actual.getDescription());
  }

  @Test
  public void testFindTransferById() {
    // given
    String mockedOriginAccount = "234-432";
    String mockedRecipientAccount = "543-433";
    String mockedDescription = "A little big thing ";
    double mockedAmount = 123.0;

    Transfer expectedTransfer = new Transfer();
    expectedTransfer.setOriginAccount(mockedOriginAccount);
    expectedTransfer.setRecipientAccount(mockedRecipientAccount);
    expectedTransfer.setDescription(mockedDescription);
    expectedTransfer.setAmount(mockedAmount);

    expectedTransfer = transferRepository.save(expectedTransfer);

    // when
    Transfer actual = transferRepository.findById(expectedTransfer.getId());

    // then
    assertNotNull(actual);

    assertEquals(expectedTransfer.getOriginAccount(), actual.getOriginAccount());
    assertEquals(expectedTransfer.getRecipientAccount(), actual.getRecipientAccount());
    assertEquals(expectedTransfer.getAmount(), actual.getAmount(), 0.001);
  }

  @Test
  public void testFindAllTransfers() {
    // given
    Transfer someTransfer = new Transfer();
    someTransfer.setOriginAccount("123");
    someTransfer.setRecipientAccount("456");
    someTransfer.setAmount(10.8);

    Transfer otherTransfer = new Transfer();
    otherTransfer.setOriginAccount("567");
    otherTransfer.setRecipientAccount("987");
    someTransfer.setAmount(430.2);

    transferRepository.save(someTransfer);
    transferRepository.save(otherTransfer);

    List<Transfer> expectedTransfers = new ArrayList<>();
    expectedTransfers.add(someTransfer);
    expectedTransfers.add(otherTransfer);

    // when
    List<Transfer> actual = transferRepository.findAll();

    // then
    assertEquals(expectedTransfers.size(), actual.size());
  }
}
