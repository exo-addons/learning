package org.exoplatform.addon.perkstore.dto;

import java.io.Serializable;
import java.util.List;

public class CategoryDTO implements Serializable {
  private Long            idCategory;
  private String          nameCategory;
  private List<CourseDTO> course;


}
