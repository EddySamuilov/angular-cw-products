package com.example.products.dtos;

import com.example.products.enums.CategoryType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryCreateDTO {

//  @NotNull(message = "Category type cannot be null!")
  private String type;

  @Size(min = 5, max = 255, message = "The description must be at least 5 and must not exceed 255 characters!")
  private String description;

  private String imageUrl;

  private String username;
}
