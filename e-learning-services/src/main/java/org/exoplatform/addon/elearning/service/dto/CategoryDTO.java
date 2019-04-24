package org.exoplatform.addon.elearning.service.dto;

import org.exoplatform.addon.elearning.entities.CategoryEntity;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
  private Long idCategory;
  private String          nameCategory;

  public CategoryDTO() {
  }

  public CategoryDTO(CategoryEntity category) {
    this.idCategory=category.getIdCategory();
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
