package com.example.products.web.rest;

import com.example.products.dtos.UserProfileDTO;
import com.example.products.dtos.UserRegisterDTO;
import com.example.products.models.User;
import com.example.products.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserResource {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<User> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
    User user = userService.register(userRegisterDTO);
    return ResponseEntity.ok(user);
  }

//  @GetMapping("/profile")
//  public ResponseEntity<UserProfileDTO> getUserProfile(String email) {
//    return userService.getUserProfile();
//  }
//
//  @PutMapping("/profile")
//  public ResponseEntity<UserProfileDTO> updateUserProfile() {
//    return userService.updateUserProfile();
//  }
}
