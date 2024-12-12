package com.example.products.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductCreateDTO {
  @NotBlank(message = "The title must not be blank!")
  private String title;

  @Size(min = 5, max = 255, message = "The description must be at least 5 and must not exceed 255 characters!")
  private String description;

  private String imageUrl;

  @NotBlank(message = "Category should be provided")
  private String categoryType;

  private BigDecimal price;

  private String username;
}
