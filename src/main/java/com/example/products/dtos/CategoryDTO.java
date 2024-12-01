package com.example.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
  private Long id;

  private String type;

  private String description;

  private String imageUrl;
}
