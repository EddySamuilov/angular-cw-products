package com.example.products.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfileDTO {
  private String username;
  private String firstName;
  private String lastName;
  private String email;
}
