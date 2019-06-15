package com.rflpazini.rogue.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import com.rflpazini.rogue.app.dataprovider.impl.CustomerRepository;
import com.rflpazini.rogue.app.entrypoint.model.Account;
import com.rflpazini.rogue.app.entrypoint.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  @Mock CustomerRepository repository;

  @InjectMocks AccountService accountService;

  @Test
  public void testDepositNewAmount() {
    // given
    String mockedId = "1234";
    double mockedAmount = 145.4;

    Customer mockedCustomer = new Customer();
    mockedCustomer.setAccountId(mockedId);

    when(repository.findById(anyString())).thenReturn(mockedCustomer);

    // when
    Account actual = accountService.deposit(mockedId, mockedAmount);

    // then
    assertEquals(mockedAmount, actual.getBalance(), 0.001);
  }

  @Test
  public void testWithdrawAmount() {
    // given
    String mockedId = "1234";
    double mockedBalance = 200;
    double mockedAmount = 145.4;

    Customer mockedCustomer = new Customer();
    mockedCustomer.setAccountId(mockedId);
    mockedCustomer.setBalance(mockedBalance);

    when(repository.findById(anyString())).thenReturn(mockedCustomer);

    // when
    Account actual = accountService.withdraw(mockedId, mockedAmount);

    // then
    assertEquals(54.6, actual.getBalance(), 0.001);
  }

  @Test(expected = Exception.class)
  public void testWithdrawAmount_withError() {
    // given
    String mockedId = "1234";
    double mockedAmount = 145.4;

    Customer mockedCustomer = new Customer();
    mockedCustomer.setAccountId(mockedId);

    when(repository.findById(anyString())).thenReturn(mockedCustomer);

    // when
    accountService.withdraw(mockedId, mockedAmount);
  }

  @Test
  public void testAccountInfo() {
    // given
    Customer mockedCustomer = new Customer();
    mockedCustomer.setBalance(12.5);

    when(repository.findById(anyString())).thenReturn(mockedCustomer);

    // when
    Account actual = accountService.getAccountInfo(anyString());

    // then
    assertEquals(mockedCustomer.getBalance(), actual.getBalance(), 0.001);
  }
}
