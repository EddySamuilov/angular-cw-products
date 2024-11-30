package com.example.products.models;

import com.example.products.enums.CategoryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  @Size(min = 5, max = 255, message = "The description must be at least 5 characters and must not exceed 255 characters!")
  private String description;

  private String imageUrl;

  @Column(nullable = false)
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
