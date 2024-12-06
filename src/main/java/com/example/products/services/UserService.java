package com.example.products.services;

import com.example.products.dtos.UserProfileDTO;
import com.example.products.dtos.UserRegisterDTO;
import com.example.products.mappers.UserMapper;
import com.example.products.models.Role;
import com.example.products.models.User;
import com.example.products.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private static final String USER_NOT_FOUND = "User with username: [%s] not found!";

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public User register(UserRegisterDTO userRegisterDTO) {
    User user = userMapper.toEntity(userRegisterDTO);

    user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
    user.setCreated(LocalDateTime.now());
    user.setModified(LocalDateTime.now());
    user.setImageURL("https://upload.wikimedia.org/wikipedia/en/thumb/3/33/Patrick_Star.svg/1200px-Patrick_Star.svg.png");

    return userRepository.save(user);
  }

  public UserProfileDTO getUserProfile(String username) {
    User user = findByUsername(username);
    return userMapper.toProfileDTO(user);
  }

  public UserProfileDTO updateUserProfile(String username, UserProfileDTO userProfileDTO) {
    User user = findByUsername(username);

    user.setUsername(userProfileDTO.getUsername());
    user.setFirstName(userProfileDTO.getFirstName());
    user.setLastName(userProfileDTO.getLastName());
    user.setEmail(userProfileDTO.getEmail());
    user.setImageURL(userProfileDTO.getImageURL());
    user.setModified(LocalDateTime.now());

    return userMapper.toProfileDTO(userRepository.save(user));
  }

  public User findByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, username)));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = findByUsername(username);
    return org.springframework.security.core.userdetails.User
        .withUsername(user.getUsername())
        .password(user.getPassword())
        .authorities(getAuthorities(user.getRoles()))
        .build();
  }

  private static List<GrantedAuthority> getAuthorities(Set<Role> userRoles) {
    return userRoles.stream()
        .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName().name()))
        .collect(Collectors.toList());
  }
}
