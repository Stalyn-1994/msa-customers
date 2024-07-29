package com.pichincha.customer.service.impl;



import static com.pichincha.customer.helper.CustomerHelper.buildResponseEntity;
import static com.pichincha.customer.util.Constants.ALREADY_IDENTIFICATION_REGISTRATION;
import static com.pichincha.customer.util.Constants.NOT_FOUND;
import static com.pichincha.customer.util.Constants.PERSON_NOT_YET_REGISTERED;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.customer.domain.db.CustomerEntity;
import com.pichincha.customer.domain.exception.BadRequestException;
import com.pichincha.customer.domain.exception.NotFoundException;
import com.pichincha.customer.repository.CustomerRepository;
import com.pichincha.customer.service.CustomerService;
import com.pichincha.customer.service.dto.request.CustomerRequestDto;
import com.pichincha.customer.service.dto.request.CustomerRequestEdit;
import com.pichincha.customer.service.dto.request.CustomerRequestLegacyDto;
import com.pichincha.customer.service.dto.request.CustomerRequestUpdateDto;
import com.pichincha.customer.service.dto.response.BaseResponseDto;
import com.pichincha.customer.service.dto.response.CustomerResponseDto;
import com.pichincha.customer.service.mapper.CustomerServiceMapper;
import jakarta.transaction.Transactional;
import java.util.Map;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerServiceImpl implements CustomerService {

  final CustomerServiceMapper customerServiceMapper;
  final CustomerRepository customerRepository;
  final ObjectMapper objectMapper;
  final KafkaServiceImpl kafkaService;

  @Override
  @Transactional
  public ResponseEntity<BaseResponseDto> save(CustomerRequestDto customerDto) {
    Optional<CustomerEntity> customerEntity = customerRepository.findCustomerEntitiesByIdentification(
        customerDto.getIdentification());
    if (customerEntity.isEmpty()) {
      String customerId = customerRepository.save(
          customerServiceMapper.toCustomerEntity(customerDto)).getClientId();
      return buildResponseEntity(CustomerResponseDto.builder()
          .customerId(customerId)
          .build(), HttpStatus.CREATED);
    }
    throw new BadRequestException(ALREADY_IDENTIFICATION_REGISTRATION);
  }

  @Override
  @Transactional
  public ResponseEntity<BaseResponseDto> update(CustomerRequestUpdateDto customerDto) {
    CustomerEntity customerEntity = customerRepository.findCustomerEntitiesByClientId(
        customerDto.getCustomerId()).orElseThrow(() -> new NotFoundException(NOT_FOUND));
    String customerId = customerRepository.save(
            customerServiceMapper.toCustomerEntityUpdated(customerDto, customerEntity.getId()))
        .getClientId();
    isCustomerUpdateState(customerDto);
    return buildResponseEntity(CustomerResponseDto.builder()
          .customerId(customerId)
          .build(), HttpStatus.OK);
    }

  private void isCustomerUpdateState(CustomerRequestUpdateDto customerDto) {
    if (Boolean.FALSE.equals(customerDto.getState())) {
      try {
        kafkaService.send("example", objectMapper.writeValueAsString(
            CustomerRequestLegacyDto.builder().identification(customerDto.getCustomerId())
                .build()));
      } catch (Exception exception) {
        log.error(exception.getMessage());
      }
    }
  }

  @Override
  @Transactional
    public ResponseEntity<BaseResponseDto> edit (Map < String, Object > customerDto, String
    customerId){
      try {
        CustomerEntity customerEntity = customerRepository.findCustomerEntitiesByClientId(
            customerId).orElseThrow(() -> new NotFoundException(NOT_FOUND));
        CustomerRequestEdit customerRequestDto = objectMapper.convertValue(customerDto,
            CustomerRequestEdit.class);
        isCustomerUpdateState(CustomerRequestUpdateDto.builder().customerId(customerId).state(customerRequestDto.getState()).build());
        String clientId = customerRepository.save(
            customerServiceMapper.editCustomerFromCustomerRequestDto(customerEntity,
                customerRequestDto)).getClientId();
        return buildResponseEntity(CustomerResponseDto.builder()
            .customerId(clientId)
            .build(), HttpStatus.OK);
      } catch (Exception exception) {
        log.error(exception.getMessage());
        throw new BadRequestException(exception.getMessage());
      }
    }

    @Override
    @Transactional
    public ResponseEntity<BaseResponseDto> delete (String customerId){
      CustomerEntity customerEntity = customerRepository.findCustomerEntitiesByClientId(
          customerId).orElseThrow(() -> new NotFoundException(NOT_FOUND));
      customerRepository.delete(customerEntity);
      return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<BaseResponseDto> findCustomerByCustomerId (String customerId){
      Optional<CustomerEntity> customerEntity = customerRepository.findCustomerEntitiesByClientId(
          customerId);
      return customerEntity.map(customer -> buildResponseEntity(customerServiceMapper
              .toCustomerInfoResponseDto(customer), HttpStatus.OK))
          .orElseGet(() -> buildResponseEntity(PERSON_NOT_YET_REGISTERED,
              HttpStatus.BAD_REQUEST));
    }
  }