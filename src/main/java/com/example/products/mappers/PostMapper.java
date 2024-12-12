package com.example.products.mappers;

import com.example.products.dtos.PostCreateDTO;
import com.example.products.dtos.PostResponseDTO;
import com.example.products.dtos.PostUpdateDTO;
import com.example.products.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PostMapper {

  @Mapping(target = "createdBy", source = "source.user.username")
  @Mapping(target = "productId", source = "source.product.id")
  PostResponseDTO toDTO(Post source);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "modified", ignore = true)
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "likes", ignore = true)
  @Mapping(target = "dislikes", ignore = true)
  @Mapping(target = "product", ignore = true)
  Post toEntity(PostCreateDTO source);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "modified", ignore = true)
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "product", ignore = true)
  Post toEntity(PostUpdateDTO source);

}
