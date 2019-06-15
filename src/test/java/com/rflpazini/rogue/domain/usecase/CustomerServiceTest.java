package com.rflpazini.rogue.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import com.rflpazini.rogue.app.dataprovider.impl.CustomerRepository;
import com.rflpazini.rogue.app.entrypoint.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

  @Mock CustomerRepository customerRepository;

  @InjectMocks CustomerService customerService;

  @Test
  public void testCreateNewCustomer() {
    // given
    String mockedName = "John Due";
    String mockedEmail = "jd@email.com";

    Customer expectedCustomer = new Customer();
    expectedCustomer.setName(mockedName);
    expectedCustomer.setEmail(mockedEmail);

    when(customerRepository.save(any(Customer.class))).thenReturn(expectedCustomer);

    // when
    Customer actual = customerService.saveCustomer(expectedCustomer);

    // then
    assertNotNull(actual);

    assertEquals(mockedEmail, actual.getEmail());
    assertEquals(mockedName, actual.getName());
  }

  @Test
  public void testFindById() {
    // given
    String mockedId = "1233213";

    Customer expectedCustomer = new Customer();
    expectedCustomer.setAccountId(mockedId);

    when(customerRepository.findById(anyString())).thenReturn(expectedCustomer);

    // when
    Customer actual = customerService.findById(mockedId);

    // then
    assertNotNull(actual);

    assertEquals(mockedId, actual.getAccountId());
  }
}
