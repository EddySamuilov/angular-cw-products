package com.example.products.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {

  @Column(nullable = false)
  private String type;

  @Column(nullable = false)
  private String description;

  private String imageUrl;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
}
