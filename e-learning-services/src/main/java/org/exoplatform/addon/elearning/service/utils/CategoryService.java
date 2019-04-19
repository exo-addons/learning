package org.exoplatform.addon.elearning.service.utils;

import org.exoplatform.addon.elearning.dao.CategoryDao;
import org.exoplatform.addon.elearning.dto.CategoryDTO;
import org.exoplatform.addon.elearning.dto.CategoryMapper;
import org.exoplatform.addon.elearning.entity.Category;

import java.util.ArrayList;
import java.util.List;

import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

public class CategoryService {
  private CategoryDao categoryDao;
  private CategoryMapper categoryMapper;
  private static final Log      LOG = ExoLogger.getExoLogger(Category.class);


  public CategoryService(CategoryDao categoryDao, CategoryMapper categoryMapper) {
    this.categoryDao =  CommonsUtils.getService(CategoryDao.class);
    this.categoryMapper = CommonsUtils.getService(CategoryMapper.class);
  }

  public List<CategoryDTO> getAllCategories() {
    try {
      //--- load all Rules
      List<Category> categories = categoryDao.findAll();
      if (categories != null) {
        return categoryMapper.categoryToCategoryDTOs(categories);
      }

    } catch (Exception e) {
      LOG.error("Error to find Categories", e.getMessage());
    }
    return null;

  }
  @ExoTransactional
  public CategoryDTO addCategory (CategoryDTO categoryDTO) {

    Category category = null;

    try {

      category= categoryDao.create(categoryMapper.categoryDTOToCategory(categoryDTO));

    } catch (Exception e) {
      LOG.error("Error to create category with Name {}", categoryDTO.getNameCategory(), e);
    }

    return categoryMapper.categoryToCategoryDTO(category);
  }

}
