package com.example.products.models;

import com.example.products.enums.CategoryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {

  @Enumerated(EnumType.STRING)
  private CategoryType type;

  @Column(nullable = false)
  private String description;

  @OneToMany(mappedBy = "category")
  private List<Product> products;
}
