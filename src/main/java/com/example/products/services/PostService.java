package com.example.products.services;

import com.example.products.dtos.PostCreateDTO;
import com.example.products.dtos.PostResponseDTO;
import com.example.products.dtos.PostUpdateDTO;
import com.example.products.exceptions.PostNotFoundException;
import com.example.products.exceptions.ProductNotFoundException;
import com.example.products.mappers.PostMapper;
import com.example.products.models.Post;
import com.example.products.models.Product;
import com.example.products.models.User;
import com.example.products.repositories.PostRepository;
import com.example.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

  public static final Logger LOG = LoggerFactory.getLogger(PostService.class);

  private static final String PRODUCT_NOT_FOUND = "Post not found!";

  private final PostRepository postRepository;
  private final PostMapper postMapper;
  private final ProductRepository productRepository;
  private final UserService userService;

  @Transactional(readOnly = true)
  public List<PostResponseDTO> getAll() {
    return postRepository.findAll()
        .stream()
        .map(postMapper::toDTO)
        .toList();
  }

  @Transactional(readOnly = true)
  public PostResponseDTO findById(String id) {
    return postRepository.findById(Long.valueOf(id))
        .map(postMapper::toDTO)
        .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
  }

  public PostResponseDTO create(PostCreateDTO postCreateDTO) {
    User user = userService.findByUsername(postCreateDTO.getUsername());
    Post post = postMapper.toEntity(postCreateDTO);

    Product productOpt = productRepository.findById(postCreateDTO.getProductId()).orElseThrow();
    productOpt.getPosts().add(post);

    post.setUser(user);
    post.setProduct(productOpt);
    post.setCreated(LocalDateTime.now());
    post.setModified(LocalDateTime.now());

    Post newPost = postRepository.save(post);
    productRepository.save(productOpt);

    return postMapper.toDTO(newPost);
  }

  public PostResponseDTO update(String id, PostUpdateDTO postUpdateDTO) {
    Post postOpt = postRepository.findById(Long.valueOf(id))
        .orElseThrow(() -> new PostNotFoundException(PRODUCT_NOT_FOUND));

    postOpt.setTitle(postUpdateDTO.getTitle());
    postOpt.setDescription(postUpdateDTO.getDescription());
    postOpt.setLikes(postUpdateDTO.getLikes());
    postOpt.setDislikes(postUpdateDTO.getDislikes());
    postOpt.setModified(LocalDateTime.now());

    return postMapper.toDTO(postRepository.save(postOpt));
  }

  public void delete(String id) {
    postRepository.deleteById(Long.valueOf(id));
  }

}
