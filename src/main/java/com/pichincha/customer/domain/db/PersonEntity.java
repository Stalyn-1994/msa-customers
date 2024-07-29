package com.pichincha.customer.domain.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public  class PersonEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @Column(nullable = false, length = 255)
  String name;
  @Column(length = 50)
  String gender;
  Integer age;
  @Column(length = 100)
  String identification;
  @Column(length = 255)
  String address;
  @Column(length = 50)
  String cellphone;

}