package com.example.products.web.rest;

import com.example.products.dtos.CategoryCreateDTO;
import com.example.products.dtos.CategoryDTO;
import com.example.products.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryResource {

  private final CategoryService categoryService;

  @Operation(summary = "Retrieves all the existing categories.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Categories not found!")
  @GetMapping
  public ResponseEntity<List<CategoryDTO>> getAll() {
    return ResponseEntity.ok(categoryService.getAll());
  }

  @Operation(summary = "Retrieves a category by the provided id.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Category not found!")
  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> findById(@PathVariable String id) {
    return ResponseEntity.ok(categoryService.findById(id));
  }

  @Operation(summary = "Creates a category.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @PostMapping("/add")
  public ResponseEntity<Long> create(@RequestBody @Valid CategoryCreateDTO categoryCreateDTO, HttpServletRequest request) {
    long categoryId = categoryService.create(categoryCreateDTO);

    URI uri = ServletUriComponentsBuilder.fromRequest(request)
        .replacePath("/api/v1/categories/{id}")
        .buildAndExpand(categoryId)
        .toUri();

    return ResponseEntity.created(uri).body(categoryId);
  }

//  @Operation(summary = "Updates a product.")
//  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
//  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
//  @ApiResponse(responseCode = "404", description = "Product not found!")
//  @PutMapping("{id}/update")
//  public ResponseEntity<Void> update(
//      @PathVariable String id,
//      @RequestBody @Valid ProductUpsertDTO productUpsertDTO,
//      HttpServletRequest request
//  ) {
//    categoryService.update(id, productUpsertDTO);
//
//    URI uri = ServletUriComponentsBuilder.fromRequest(request)
//        .replacePath("/api/v1/products/{id}")
//        .buildAndExpand(id)
//        .toUri();
//
//    return ResponseEntity.created(uri).build();
//  }

  @Operation(summary = "Removes a category by id.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Product not found!")
  @PutMapping("{id}/delete")
  public ResponseEntity<Void> update(@PathVariable String id) {
    categoryService.delete(id);

    return ResponseEntity.ok().build();
  }
}
