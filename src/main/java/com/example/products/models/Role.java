package com.example.products.models;

import com.example.products.enums.RoleName;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role extends BaseEntity {

  @Enumerated(EnumType.STRING)
  private RoleName roleName;

}