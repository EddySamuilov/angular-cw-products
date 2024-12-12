package com.example.products.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCreateDTO {

  @NotBlank(message = "Title must be provided!")
  private String title;

  @NotBlank(message = "Title must be provided!")
  private String description;

  private String username;
}
