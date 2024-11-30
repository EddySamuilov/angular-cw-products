package com.example.products.mappers;

import com.example.products.dtos.CategoryDTO;
import com.example.products.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CategoryMapper {

  CategoryDTO toDTO(Category category);

  Category toEntity(CategoryDTO source);
}
