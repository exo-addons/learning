package org.exoplatform.addon.elearning.dto;

import org.exoplatform.addon.elearning.entity.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
  private String          nameCategory;

  public CategoryDTO() {
  }

  public CategoryDTO(Category category) {
    this.nameCategory = category.getNameCategory();
  }
  public String getNameCategory() {
    return nameCategory;
  }

  public void setNameCategory(String nameCategory) {
    this.nameCategory = nameCategory;
  }
}
