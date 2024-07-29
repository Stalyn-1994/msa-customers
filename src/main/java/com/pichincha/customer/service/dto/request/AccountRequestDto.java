package com.pichincha.customer.service.dto.request;


import com.pichincha.customer.domain.enums.AccountEnum;
import com.pichincha.customer.domain.validations.AccountValidation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountRequestDto {

  @AccountValidation
  AccountEnum type;
  @Builder.Default
  Boolean status = false;
  String name;
  @Builder.Default
  double initialBalance = 0.0;
}
