package com.example.products.services;

import com.example.products.dtos.CategoryDTO;
import com.example.products.enums.CategoryType;
import com.example.products.exceptions.CategoryNotFoundException;
import com.example.products.mappers.CategoryMapper;
import com.example.products.models.Category;
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

  public long create(CategoryDTO categoryDTO) {
    Category product = categoryMapper.toEntity(categoryDTO);
    product.setCreated(LocalDateTime.now());
    product.setModified(LocalDateTime.now());

    return categoryRepository.save(product).getId();
  }

  public void delete(String id) {
    categoryRepository.deleteById(Long.valueOf(id));
  }

  public CategoryDTO findByType(CategoryType type) {
    return categoryRepository.findByType(type)
        .map(categoryMapper::toDTO)
        .orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NOT_FOUND));
  }
}
