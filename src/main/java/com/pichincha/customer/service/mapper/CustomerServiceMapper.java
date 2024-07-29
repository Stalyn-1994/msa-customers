package com.pichincha.customer.service.mapper;



import com.pichincha.customer.domain.db.CustomerEntity;
import com.pichincha.customer.domain.db.PersonEntity;
import com.pichincha.customer.service.dto.request.CustomerRequestDto;
import com.pichincha.customer.service.dto.request.CustomerRequestEdit;
import com.pichincha.customer.service.dto.request.CustomerRequestUpdateDto;
import com.pichincha.customer.service.dto.response.CustomerInfoResponseDto;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class CustomerServiceMapper {

  @Mapping(target = "id",ignore = true)
  @Mapping(target = "name", source = "customerDto.name")
  @Mapping(target = "gender", source = "customerDto.gender")
  @Mapping(target = "age", source = "customerDto.age")
  @Mapping(target = "identification", source = "customerDto.identification")
  @Mapping(target = "address", source = "customerDto.address")
  @Mapping(target = "cellphone", source = "customerDto.cellphone")
  @Mapping(target = "password", source = "customerDto.password")
  @Mapping(target = "state", source = "customerDto.state")
  @Mapping(target = "clientId", expression = "java(generateClientId())")
  public abstract CustomerEntity toCustomerEntity(CustomerRequestDto customerDto);

  @Mapping(target = "name", source = "personEntity.name")
  @Mapping(target = "gender", source = "personEntity.gender")
  @Mapping(target = "age", source = "personEntity.age")
  @Mapping(target = "identification", source = "customerDto.identification")
  @Mapping(target = "address", source = "personEntity.address")
  @Mapping(target = "cellphone", source = "personEntity.cellphone")
  @Mapping(target = "password", source = "customerDto.password")
  @Mapping(target = "state", source = "customerDto.state")
  @Mapping(target = "clientId", expression = "java(generateClientId())")
  @Mapping(target = "id", source = "personEntity.id")
  public abstract CustomerEntity toCustomerEntity(CustomerRequestDto customerDto, PersonEntity personEntity);

  public String generateClientId() {
    return String.valueOf(UUID.randomUUID());
  }

  public CustomerEntity toCustomerEntityUpdated(CustomerRequestUpdateDto customerDto, Long id) {
    CustomerEntity customerEntity = toCustomerEntity(customerDto);
    customerEntity.setClientId(customerDto.getCustomerId());
    customerEntity.setId(id);
    return customerEntity;
  }

  public abstract CustomerInfoResponseDto toCustomerInfoResponseDto(CustomerEntity customer);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "clientId", ignore = true)
  @Mapping(target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, source = "customerDto.name")
  @Mapping(target = "password", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, source = "customerDto.password")
  @Mapping(target = "state", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, source = "customerDto.state")
  @Mapping(target = "gender", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, source = "customerDto.gender")
  @Mapping(target = "age", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, source = "customerDto.age")
  @Mapping(target = "identification", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, source = "customerDto.identification")
  @Mapping(target = "address", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, source = "customerDto.address")
  @Mapping(target = "cellphone", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, source = "customerDto.cellphone")
  public abstract CustomerEntity editCustomerFromCustomerRequestDto(@MappingTarget CustomerEntity customer, CustomerRequestEdit customerDto);
}