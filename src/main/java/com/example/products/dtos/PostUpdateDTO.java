package com.example.products.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostUpdateDTO {
  @NotBlank(message = "Title must be provided!")
  private String title;

  @NotBlank(message = "Title must be provided!")
  private String description;

  private int likes;
  private int dislikes;

  private String username;
}
