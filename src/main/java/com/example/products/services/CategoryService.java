package com.example.products.services;

import com.example.products.dtos.CategoryCreateDTO;
import com.example.products.dtos.CategoryDTO;
import com.example.products.enums.CategoryType;
import com.example.products.exceptions.CategoryNotFoundException;
import com.example.products.mappers.CategoryMapper;
import com.example.products.models.Category;
import com.example.products.models.User;
import com.example.products.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
  private static final String CATEGORY_NOT_FOUND = "Category not found!";

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;
  private final UserService userService;

  @Transactional(readOnly = true)
  public List<CategoryDTO> getAll() {
    return categoryRepository.findAll()
        .stream()
        .map(categoryMapper::toDTO)
        .toList();
  }

  @Transactional(readOnly = true)
  public CategoryDTO findById(String id) {
    return categoryRepository.findById(Long.valueOf(id))
        .map(categoryMapper::toDTO)
        .orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NOT_FOUND));
  }

  public long create(CategoryCreateDTO categoryCreateDTO) {
    User user = userService.findByUsername(categoryCreateDTO.getUsername());
    Category category = categoryMapper.toEntity(categoryCreateDTO);
    category.setType(categoryCreateDTO.getType().toUpperCase());
    category.setUser(user);
    category.setCreated(LocalDateTime.now());
    category.setModified(LocalDateTime.now());

    return categoryRepository.save(category).getId();
  }

  public void delete(String id) {
    categoryRepository.deleteById(Long.valueOf(id));
  }

}
