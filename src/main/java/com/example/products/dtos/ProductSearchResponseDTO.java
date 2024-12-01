package com.example.products.dtos;

import com.example.products.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchResponseDTO {
  private Long id;
  private String title;
  private String description;
  private Category category;
  private String imageUrl;
  private BigDecimal price;
  private LocalDateTime created;
  private LocalDateTime modified;
}
