package com.example.products.mappers;

import com.example.products.dtos.ProductSearchResponseDTO;
import com.example.products.dtos.ProductUpsertDTO;
import com.example.products.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

  ProductSearchResponseDTO toDTO(Product source);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "modified", ignore = true)
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "category", ignore = true)
  Product toEntity(ProductUpsertDTO source);

}
