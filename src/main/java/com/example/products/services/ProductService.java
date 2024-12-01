package com.example.products.services;

import com.example.products.dtos.CategoryDTO;
import com.example.products.dtos.ProductSearchResponseDTO;
import com.example.products.dtos.ProductUpsertDTO;
import com.example.products.exceptions.ProductNotFoundException;
import com.example.products.mappers.CategoryMapper;
import com.example.products.mappers.ProductMapper;
import com.example.products.models.Category;
import com.example.products.models.Product;
import com.example.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

  public static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

  private static final String PRODUCT_NOT_FOUND = "Product not found!";

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final CategoryMapper categoryMapper;
  private final CategoryService categoryService;

  @Transactional(readOnly = true)
  public List<ProductSearchResponseDTO> getAll() {
    return productRepository.findAll()
        .stream()
        .map(productMapper::toDTO)
        .toList();
  }

  @Transactional(readOnly = true)
  public ProductSearchResponseDTO findById(String id) {
    return productRepository.findById(Long.valueOf(id))
        .map(productMapper::toDTO)
        .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
  }

  public long create(ProductUpsertDTO productCreateDTO) {
    Product product = productMapper.toEntity(productCreateDTO);
    product.setCreated(LocalDateTime.now());
    product.setModified(LocalDateTime.now());

    return productRepository.save(product).getId();
  }

  public ProductSearchResponseDTO update(String id, ProductUpsertDTO productUpdateDTO) {
    Product productOpt = productRepository.findById(Long.valueOf(id))
        .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));

//    CategoryDTO categoryDTO = categoryService.findById(productUpdateDTO.getCategoryId());
    Category category = categoryMapper.toEntity(productUpdateDTO.getCategory());

    productOpt.setTitle(productUpdateDTO.getTitle());
    productOpt.setDescription(productUpdateDTO.getDescription());
    productOpt.setPrice(productUpdateDTO.getPrice());
    productOpt.setCategory(category);
    productOpt.setImageUrl(productOpt.getImageUrl());
    productOpt.setModified(LocalDateTime.now());

    return productMapper.toDTO(productRepository.save(productOpt));
  }

  public void delete(String id) {
    productRepository.deleteById(Long.valueOf(id));
  }

  public List<ProductSearchResponseDTO> getProductsByCategory(String categoryId) {
    List<Product> productsByCategory = productRepository.findAllByCategoryId(Long.valueOf(categoryId));
    return productsByCategory.stream()
        .map(productMapper::toDTO)
        .toList();
  }
}
