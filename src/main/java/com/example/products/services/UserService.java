package com.example.products.services;

import com.example.products.dtos.UserProfileDTO;
import com.example.products.dtos.UserRegisterDTO;
import com.example.products.mappers.UserMapper;
import com.example.products.models.User;
import com.example.products.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public User register(UserRegisterDTO userRegisterDTO) {
    User user = userMapper.toEntity(userRegisterDTO);

    user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
    user.setPassword(userRegisterDTO.getPassword());
    user.setCreated(LocalDateTime.now());
    user.setModified(LocalDateTime.now());
    user.setImageURL("https://upload.wikimedia.org/wikipedia/en/thumb/3/33/Patrick_Star.svg/1200px-Patrick_Star.svg.png");

    return userRepository.save(user);
  }

  public UserProfileDTO getUserProfile(String username) {
    return null;
  }

  public UserProfileDTO updateUserProfile(String username) {
    return null;
  }
}
