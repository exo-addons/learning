package org.exoplatform.addon.elearning.service.utils;

import org.exoplatform.addon.elearning.dao.CategoryDao;
import org.exoplatform.addon.elearning.dto.CategoryDTO;
import org.exoplatform.addon.elearning.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CategoryService {
  private CategoryDao categoryDao;

  public CategoryService(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  public void setCategoryDao(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }


  public CategoryDTO getCategorie(Long id){
    Category cat=categoryDao.find(id);
    CategoryDTO categoryDTO=new CategoryDTO();
    categoryDTO.setNameCategory(cat.getNameCategory());
    return categoryDTO;

  }
  public List<CategoryDTO> getAllCategoryDtos(){
    List<CategoryDTO> list1=new ArrayList<CategoryDTO>();
    List<Category> list2= categoryDao.findAll();
    int i=0;
    while(i<list2.size()){
      CategoryDTO cdto=new CategoryDTO();
      cdto.setNameCategory(list2.get(i).getNameCategory());
      list1.add(cdto);
      i++;

    }



    return list1;
  }
  public Category addCategory(CategoryDTO categoryDTO){
    Category category=new Category(categoryDTO.getNameCategory());
    categoryDao.create(category);
    return category;
  }
}
