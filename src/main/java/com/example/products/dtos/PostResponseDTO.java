package com.example.products.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResponseDTO {
  private Long id;
  private String title;

  private String description;

  private int likes;
  private int dislikes;

  private String createdBy;
  private long productId;
}
