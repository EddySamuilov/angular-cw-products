package com.example.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchResponseDTO {
  private String title;
  private String description;
  private String type;
  private String imageUrl;
  private BigDecimal price;
  private LocalDateTime created;
  private LocalDateTime modified;
}
