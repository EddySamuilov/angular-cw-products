package com.example.products.dtos;

import com.example.products.enums.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductUpsertDTO {

  @NotBlank(message = "The title must not be blank!")
  private String title;

  @Size(min = 5, max = 255, message = "The description must be at least 5 and must not exceed 255 characters!")
  private String description;

  @NotNull(message = "The product type must be provided!")
  @Enumerated(EnumType.STRING)
  private ProductType type;
}
