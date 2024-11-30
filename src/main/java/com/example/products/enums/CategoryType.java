package com.example.products.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CategoryType {
  ELECTRONICS("Electronic gadgets"),
  CLOTHES("Brand new clothes"),
  BOOKS("The most interesting books"),
  FOOD("The most delicious food"),
  FURNITURE("Luxury furniture for everyone"),
  OTHER("All the other stuff");

  private final String label;
}