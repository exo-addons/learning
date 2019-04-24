package org.exoplatform.addon.elearning.service.mapper;

import org.exoplatform.addon.elearning.entities.CategoryEntity;
import org.exoplatform.addon.elearning.service.dto.CategoryDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CategoryMapper {
  public CategoryMapper() {
  }
  public CategoryDTO categoryToCategoryDTO(CategoryEntity category) {
    return new CategoryDTO(category);
  }
  public List<CategoryDTO> categoryToCategoryDTOs(List<CategoryEntity>categories){
    return categories.stream()
                  .filter(Objects::nonNull)
                  .map(this::categoryToCategoryDTO)
                  .collect(Collectors.toList());
  }

  public CategoryEntity categoryDTOToCategory(CategoryDTO categoryDTO){
    try {
      if(categoryDTO==null){
        return null;
      }else{
        CategoryEntity category=new CategoryEntity();
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
