package com.rflpazini.rogue.app.dataprovider.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.rflpazini.rogue.app.dataprovider.DataBase;
import com.rflpazini.rogue.app.entrypoint.model.Customer;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerRepositoryTest {

  private CustomerRepository repository;

  @Before
  public void setup() {
    this.repository = new CustomerRepository();
  }

  @After
  public void clear() {
    DataBase.INSTANCE.user().clear();
  }

  @Test
  public void testCreateNewCustomer() {
    // given
    String mockedName = "John Due";
    String mockedEmail = "jd@email.com";

    Customer expectedCustomer = new Customer();
    expectedCustomer.setName(mockedName);
    expectedCustomer.setEmail(mockedEmail);

    // when
    Customer actual = repository.save(expectedCustomer);

    // then
    assertEquals(expectedCustomer.getName(), actual.getName());
    assertEquals(expectedCustomer.getEmail(), actual.getEmail());

    assertNotNull(actual.getAccountId());
    assertNotNull(actual.getCreatedAt());
  }

  @Test
  public void testFindUserById() {
    // given
    String mockedName = "John Due";
    String mockedEmail = "jd@email.com";

    Customer expectedCustomer = new Customer();
    expectedCustomer.setName(mockedName);
    expectedCustomer.setEmail(mockedEmail);

    expectedCustomer = repository.save(expectedCustomer);

    // when
    Customer actual = repository.findById(expectedCustomer.getAccountId());

    // then
    assertNotNull(actual);

    assertEquals(expectedCustomer.getName(), actual.getName());
    assertEquals(expectedCustomer.getEmail(), actual.getEmail());
  }

  @Test
  public void testUpdateCustomer() {
    // given
    String mockedName = "John Due";
    String mockedEmail = "jd@email.com";

    Customer expectedCustomer = new Customer();
    expectedCustomer.setName(mockedName);
    expectedCustomer.setEmail(mockedEmail);

    expectedCustomer = repository.save(expectedCustomer);

    // when
    expectedCustomer.setBalance(150.5);
    Customer actual = repository.update(expectedCustomer);

    // then
    assertEquals(expectedCustomer.getName(), actual.getName());
    assertEquals(expectedCustomer.getEmail(), actual.getEmail());
    assertEquals(expectedCustomer.getBalance(), actual.getBalance(), 0.001);
    assertEquals(expectedCustomer.getAccountId(), actual.getAccountId());

    assertNotNull(actual.getUpdatedAt());
  }

  @Test(expected = Exception.class)
  public void testFindUserById_withError() {
    // given
    String mockedName = "John Due";
    String mockedEmail = "jd@email.com";
    String mockedId = "123";

    Customer expectedCustomer = new Customer();
    expectedCustomer.setName(mockedName);
    expectedCustomer.setEmail(mockedEmail);

    repository.save(expectedCustomer);

    // when
    repository.findById(mockedId);
  }

  @Test
  public void testFindAllUsers() {
    // given
    Customer someCustomer = new Customer();
    someCustomer.setName("Tony Stark");
    someCustomer.setEmail("tony@stark.com");

    Customer otherCustomer = new Customer();
    otherCustomer.setName("Albert Einstein");
    otherCustomer.setEmail("einstein@mail.com");

    repository.save(someCustomer);
    repository.save(otherCustomer);

    List<Customer> expectedCustomers = new ArrayList<>();
    expectedCustomers.add(someCustomer);
    expectedCustomers.add(otherCustomer);

    // when
    List<Customer> actual = repository.findAll();

    // then
    assertEquals(expectedCustomers.size(), actual.size());
  }

  @Test
  public void testDeleteCustomer() {
    // given
    Customer someCustomer = new Customer();
    someCustomer.setName("Tony Stark");
    someCustomer.setEmail("tony@stark.com");

    someCustomer = repository.save(someCustomer);

    List<Customer> expectedCustomers = new ArrayList<>();

    // when
    repository.delete(someCustomer.getAccountId());
    List<Customer> actual = repository.findAll();

    assertEquals(expectedCustomers.size(), actual.size());
  }

  @Test(expected = Exception.class)
  public void testDeleteCustomer_withError() {
    // given
    Customer someCustomer = new Customer();
    someCustomer.setName("Tony Stark");
    someCustomer.setEmail("tony@stark.com");

    repository.save(someCustomer);

    // when
    repository.delete("123-323");
  }
}
