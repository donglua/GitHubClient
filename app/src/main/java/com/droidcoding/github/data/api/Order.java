package com.droidcoding.github.data.api;

public enum Order {
  ASC("asc"),
  DESC("desc");

  private final String value;

  Order(String value) {
    this.value = value;
  }

  @Override public String toString() {
    return value;
  }
}
