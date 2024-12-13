package com.example.products.web.rest;

import com.example.products.dtos.PagedDTO;
import com.example.products.dtos.ProductCreateDTO;
import com.example.products.dtos.ProductSearchResponseDTO;
import com.example.products.dtos.ProductUpsertDTO;
import com.example.products.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductResource {

  private final ProductService productService;

  @Operation(summary = "Retrieves all the existing products.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Products not found!")
  @GetMapping
  public ResponseEntity<PagedDTO<ProductSearchResponseDTO>> getAll(
      @RequestParam(value = "categoryId", required = false) String categoryId,
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "itemsPerPage", defaultValue = "10") int itemsPerPage
  ) {
    if (categoryId == null) {
      return ResponseEntity.ok(productService.getAll(page, itemsPerPage));
    }

    return ResponseEntity.ok(productService.getProductsByCategory(categoryId, page, itemsPerPage));
  }

  @Operation(summary = "Retrieves a product by the provided id.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Product not found!")
  @GetMapping("/{id}")
  public ResponseEntity<ProductSearchResponseDTO> findById(@PathVariable String id) {
    return ResponseEntity.ok(productService.findById(id));
  }

  @Operation(summary = "Creates a product.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @PostMapping("/add")
  public ResponseEntity<Long> create(@RequestBody @Valid ProductCreateDTO productCreateDTO, HttpServletRequest request) {
    long productId = productService.create(productCreateDTO);

    URI uri = ServletUriComponentsBuilder.fromRequest(request)
        .replacePath("/api/v1/products/{id}")
        .buildAndExpand(productId)
        .toUri();

    return ResponseEntity.created(uri).body(productId);
  }

  @Operation(summary = "Updates a product.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Product not found!")
  @PutMapping("/{id}")
  public ResponseEntity<ProductSearchResponseDTO> update(
      @PathVariable String id,
      @RequestBody @Valid ProductUpsertDTO productUpsertDTO,
      HttpServletRequest request
  ) {
    ProductSearchResponseDTO responseDTO = productService.update(id, productUpsertDTO);

    URI uri = ServletUriComponentsBuilder.fromRequest(request)
        .replacePath("/api/v1/products/{id}")
        .buildAndExpand(id)
        .toUri();

    return ResponseEntity.created(uri).body(responseDTO);
  }

  @Operation(summary = "Removes a product by id.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Product not found!")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    productService.delete(id);
    return ResponseEntity.ok().build();
  }
}
