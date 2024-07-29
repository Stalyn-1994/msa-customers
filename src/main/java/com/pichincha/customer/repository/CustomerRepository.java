package com.pichincha.customer.repository;


import com.pichincha.customer.domain.db.CustomerEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

  Optional<CustomerEntity> findCustomerEntitiesByClientId(String id);

  Optional<CustomerEntity> findCustomerEntitiesByIdentification(String identification);
}
