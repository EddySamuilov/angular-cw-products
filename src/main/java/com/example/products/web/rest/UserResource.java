package com.example.products.web.rest;

import com.example.products.dtos.UserProfileDTO;
import com.example.products.dtos.UserRegisterDTO;
import com.example.products.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
public class UserResource {

  private final UserService userService;

//  @PostMapping("/register")
//  public ResponseEntity<Void> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
//    String userId = userService.registerUser();
//
//    URI uri = ServletUriComponentsBuilder.fromRequest(userRegisterDTO)
//        .replacePath("/api/v1/users/profile")
//        .buildAndExpand(userId)
//        .toUri();
//
//    return ResponseEntity.created(uri).build();
//  }

//  @GetMapping("/profile")
//  public ResponseEntity<UserProfileDTO> getUserProfile() {
//    return userService.getUserProfile();
//  }

//  @PutMapping("/profile")
//  public ResponseEntity<UserProfileDTO> updateUserProfile() {
//    return userService.updateUserProfile();
//  }
}
