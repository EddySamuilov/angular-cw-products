package com.example.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagedDTO<T> {
  private List<T> content;
  private int currentPage;
  private int totalPages;
  private long totalItems;
}
