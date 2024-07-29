package com.pichincha.customer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.pichincha.customer.domain.db.CustomerEntity;
import jakarta.validation.ConstraintViolationException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class CustomerRepositoryTest {

  @Autowired
  CustomerRepository customerRepository;
  CustomerEntity customer;

  @BeforeEach
  void init() {
    customer = CustomerEntity.builder().gender("Masculino").clientId("C123")
        .identification("11111111").password("Recre@.25").state(true).name("Juan").build();
  }

  @Test
  void givenCustomerEntityWhenAllFieldsAllOkShouldSaveCustomer() {
    customerRepository.save(customer);
    Optional<CustomerEntity> foundCustomer = customerRepository.findById(customer.getId());
    assertTrue(foundCustomer.isPresent());
    assertEquals("Juan", foundCustomer.get().getName());
  }

  @Test
  void givenCustomerEntityWhenThereIsErrorAllOkShouldNotSave() {
    customer.setPassword("1234");
    assertThrows(ConstraintViolationException.class, () -> {
      customerRepository.save(customer);
    });
  }
}
