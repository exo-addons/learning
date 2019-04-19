package org.exoplatform.addon.elearning.dto;

import org.exoplatform.addon.elearning.entity.Category;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CategoryMapper {
  public CategoryMapper() {
  }
  public CategoryDTO categoryToCategoryDTO(Category category) {
    return new CategoryDTO(category);
  }
  public List<CategoryDTO> categoryToCategoryDTOs(List<Category>categories){
    return categories.stream()
                  .filter(Objects::nonNull)
                  .map(this::categoryToCategoryDTO)
                  .collect(Collectors.toList());
  }

  public Category categoryDTOToCategory(CategoryDTO categoryDTO){
    try {
      if(categoryDTO==null){
        return null;
      }else{
        Category category=new Category();
        category.setIdCategory(category.getIdCategory());
        category.setNameCategory(categoryDTO.getNameCategory());
        return category;
      }
    }catch(Exception pe){
      pe.printStackTrace();
    }
    return null;
  }
}
