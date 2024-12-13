package com.example.products.services;

import com.example.products.dtos.PagedDTO;
import com.example.products.dtos.ProductCreateDTO;
import com.example.products.dtos.ProductSearchResponseDTO;
import com.example.products.dtos.ProductUpsertDTO;
import com.example.products.exceptions.CategoryNotFoundException;
import com.example.products.exceptions.ProductNotFoundException;
import com.example.products.mappers.CategoryMapper;
import com.example.products.mappers.ProductMapper;
import com.example.products.models.Category;
import com.example.products.models.Product;
import com.example.products.models.User;
import com.example.products.repositories.CategoryRepository;
import com.example.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  private final CategoryRepository categoryRepository;
  private final UserService userService;

  @Transactional(readOnly = true)
  public PagedDTO<ProductSearchResponseDTO> getAll(int page, int itemsPerPage) {
    Pageable pageable = PageRequest.of(page, itemsPerPage);
    Page<Product> products = productRepository.findAll(pageable);

    return toPageDTO(products);
  }

  @Transactional(readOnly = true)
  public ProductSearchResponseDTO findById(String id) {
    return productRepository.findById(Long.valueOf(id))
        .map(productMapper::toDTO)
        .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
  }

  public long create(ProductCreateDTO productCreateDTO) {
    User user = userService.findByUsername(productCreateDTO.getUsername());
    Product product = productMapper.toEntity(productCreateDTO);
    Category category = categoryRepository.findByType(productCreateDTO.getCategoryType())
        .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

    product.setUser(user);
    product.setCategory(category);
    product.setCreated(LocalDateTime.now());
    product.setModified(LocalDateTime.now());

    return productRepository.save(product).getId();
  }

  public ProductSearchResponseDTO update(String id, ProductUpsertDTO productUpdateDTO) {
    Product productOpt = productRepository.findById(Long.valueOf(id))
        .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));

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

  public PagedDTO<ProductSearchResponseDTO> getProductsByCategory(String categoryId, int page, int itemsPerPage) {
    Pageable pageable = PageRequest.of(page, itemsPerPage);
    Page<Product> productsByCategory = productRepository.findAllByCategoryId(Long.valueOf(categoryId), pageable);

    return toPageDTO(productsByCategory);
  }

  private PagedDTO<ProductSearchResponseDTO> toPageDTO(Page<Product> productPage) {
    List<ProductSearchResponseDTO> productDTOs = productPage.getContent()
        .stream()
        .map(productMapper::toDTO)
        .toList();

    return new PagedDTO<>(
        productDTOs,
        productPage.getNumber(),
        productPage.getTotalPages(),
        productPage.getTotalElements()
    );
  }
}
