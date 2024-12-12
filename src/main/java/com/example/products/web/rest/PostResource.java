package com.example.products.web.rest;

import com.example.products.dtos.PostCreateDTO;
import com.example.products.dtos.PostResponseDTO;
import com.example.products.dtos.PostUpdateDTO;
import com.example.products.services.PostService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
public class PostResource {

  private final PostService postService;

  @Operation(summary = "Retrieves all the existing posts.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Products not found!")
  @GetMapping
  public ResponseEntity<List<PostResponseDTO>> getAll() {
    return ResponseEntity.ok(postService.getAll());
  }

  @Operation(summary = "Retrieves a post by the provided id.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Product not found!")
  @GetMapping("/{id}")
  public ResponseEntity<PostResponseDTO> findById(@PathVariable String id) {
    return ResponseEntity.ok(postService.findById(id));
  }

  @Operation(summary = "Creates a post.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @PostMapping("/add")
  public ResponseEntity<PostResponseDTO> create(@RequestBody @Valid PostCreateDTO postCreateDTO, HttpServletRequest request) {
    PostResponseDTO postResponseDTO = postService.create(postCreateDTO);

    URI uri = ServletUriComponentsBuilder.fromRequest(request)
        .replacePath("/api/v1/posts/{id}")
        .buildAndExpand(postResponseDTO)
        .toUri();

    return ResponseEntity.created(uri).body(postResponseDTO);
  }

  @Operation(summary = "Updates a post.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Product not found!")
  @PutMapping("/{id}")
  public ResponseEntity<PostResponseDTO> update(
      @PathVariable String id,
      @RequestBody @Valid PostUpdateDTO postUpdateDTO,
      HttpServletRequest request
  ) {
    PostResponseDTO responseDTO = postService.update(id, postUpdateDTO);

    URI uri = ServletUriComponentsBuilder.fromRequest(request)
        .replacePath("/api/v1/posts/{id}")
        .buildAndExpand(id)
        .toUri();

    return ResponseEntity.created(uri).body(responseDTO);
  }

  @Operation(summary = "Removes a post by id.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Product not found!")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    postService.delete(id);
    return ResponseEntity.ok().build();
  }
}
