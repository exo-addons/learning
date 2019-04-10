package org.exoplatform.addon.perkstore.dto;

import org.exoplatform.addon.perkstore.entity.Category;

import java.io.Serializable;
import java.util.List;

public class CategoryDTO implements Serializable {
  private Long            idCategory;
  private String          nameCategory;

  public CategoryDTO() {
  }

  public CategoryDTO(Category category) {
    this.nameCategory = category.getNameCategory();
  }

  public Long getIdCategory() {
    return idCategory;
  }

  public void setIdCategory(Long idCategory) {
    this.idCategory = idCategory;
  }

  public String getNameCategory() {
    return nameCategory;
  }

  public void setNameCategory(String nameCategory) {
    this.nameCategory = nameCategory;
  }
}
