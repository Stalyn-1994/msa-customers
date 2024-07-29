package com.pichincha.customer.db;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.pichincha.customer.domain.db.CustomerEntity;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerEntityTest {

  public static final String CLIENT_ID_CONSTRAINT = "Expected a constraint violation for clientId";
  public static final String NULL_CLIENT_ID = "Expected a constraint violation for null clientId";
  public static final String NULL_PASSWORD = "Expected a constraint violation for null password";
  public static final String STATE_NULL_CONSTRAINT = "Expected a constraint violation for null state";
  public static final String CONSTRAINT_PASSWORD = "Expected a constraint violation for password";
  public static final String VIOLATIONS_CONSTRAINT = "Expected no constraint violations";
  public static final String CLIENT_ID = "12345";
  public static final String PASSWORD = "Pass@1234";
  private static Validator validator;
  CustomerEntity customer = new CustomerEntity();

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    customer.setClientId(CLIENT_ID);
    customer.setPassword(PASSWORD);
    customer.setState(true);
  }

  @Test
  void testValidCustomerEntity() {
    assertTrue(validator.validate(customer).isEmpty(), VIOLATIONS_CONSTRAINT);
  }

  @Test
  void testCustomerEntityWithEmptyClientId() {
    customer.setClientId("");

    assertFalse(validator.validateProperty(customer, "clientId").isEmpty(), CLIENT_ID_CONSTRAINT);
  }

  @Test
  void testCustomerEntityWithShortPassword() {
    customer.setPassword("short");

    assertFalse(validator.validateProperty(customer, "password").isEmpty(), CONSTRAINT_PASSWORD);
  }

  @Test
  void testCustomerEntityWithNullClientId() {
    customer.setClientId(null);

    assertFalse(validator.validateProperty(customer, "clientId").isEmpty(), NULL_CLIENT_ID);
  }

  @Test
  void testCustomerEntityWithNullPassword() {
    customer.setPassword(null);

    assertFalse(validator.validateProperty(customer, "password").isEmpty(), NULL_PASSWORD);
  }

  @Test
  void testCustomerEntityWithNullState() {
    customer.setState(null);

    assertFalse(validator.validateProperty(customer, "state").isEmpty(), STATE_NULL_CONSTRAINT);
  }
}
