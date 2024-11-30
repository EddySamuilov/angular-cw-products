package com.example.products.dtos;

import com.example.products.models.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductUpsertDTO {

  @NotBlank(message = "The title must not be blank!")
  private String title;

  @Size(min = 5, max = 255, message = "The description must be at least 5 and must not exceed 255 characters!")
  private String description;

  private String userId;

  private String imageUrl;

  private BigDecimal price;

  private Category category;
}
