package org.exoplatform.addon.elearning.service.dto;

import java.io.Serializable;

import lombok.Data;

public @Data class CourseDTO implements Serializable {

  private Long   courseId;

  private String courseTitle;

  private String courseDescription;

  public CourseDTO() {
    super();
  }

}
