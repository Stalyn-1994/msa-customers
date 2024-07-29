package com.pichincha.customer.service;


import com.pichincha.customer.service.dto.request.CustomerRequestDto;
import com.pichincha.customer.service.dto.request.CustomerRequestUpdateDto;
import com.pichincha.customer.service.dto.response.BaseResponseDto;
import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

  ResponseEntity<BaseResponseDto> save(CustomerRequestDto customerDto);

  ResponseEntity<BaseResponseDto> update(CustomerRequestUpdateDto customerDto);

  ResponseEntity<BaseResponseDto> edit(Map<String, Object> customerDto, String identification);

  ResponseEntity<BaseResponseDto> delete(String identification);
  ResponseEntity<BaseResponseDto> findCustomerByCustomerId(String customerId);
}