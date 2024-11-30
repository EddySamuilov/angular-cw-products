package com.example.products.mappers;

import com.example.products.dtos.CategoryDTO;
import com.example.products.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CategoryMapper {

  CategoryDTO toDTO(Category category);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "modified", ignore = true)
  Category toEntity(CategoryDTO source);
}
