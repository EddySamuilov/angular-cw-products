package com.example.products.services;

import com.example.products.dtos.UserProfileDTO;
import com.example.products.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserProfileDTO getUserProfile(String username) {
    return null;
  }
}
