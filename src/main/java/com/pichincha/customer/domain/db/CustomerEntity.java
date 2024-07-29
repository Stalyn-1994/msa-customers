package com.pichincha.customer.domain.db;


import com.pichincha.customer.helper.CryptoConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "customer")
public class CustomerEntity extends PersonEntity {

  @Column(length = 50, name = "client_id", unique = true)
  @NotNull(message = "Client ID cannot be null")
  @Size(min = 1, max = 50, message = "Client ID must be between 1 and 50 characters")
  private String clientId;
  @NotNull(message  = "Password cannot be null")
  @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain at least one digit, one lowercase, one uppercase, one special character, and no whitespaces")
  @Convert(converter = CryptoConverter.class)
  private String password;
  @NotNull(message = "State cannot be null")
  private Boolean state;
}