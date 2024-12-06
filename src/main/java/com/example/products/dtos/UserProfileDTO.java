package com.example.products.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserProfileDTO {
  private String username;
  private String firstName;
  private String lastName;
  private String imageURL;
  private String email;
  private LocalDateTime created;
  private LocalDateTime modified;
}
