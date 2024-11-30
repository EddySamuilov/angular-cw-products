package com.example.products.dtos;

import jakarta.validation.constraints.NotBlank;
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

  private String userId;
}
