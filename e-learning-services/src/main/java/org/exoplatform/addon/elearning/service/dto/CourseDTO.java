package org.exoplatform.addon.elearning.service.dto;

import org.exoplatform.addon.elearning.entities.CourseEntity;

import java.io.Serializable;

public class CourseDTO implements Serializable {

  private Long   courseId;

  private String courseTitle;

  private String courseDescription;

  public CourseDTO(CourseEntity course) {

    this.courseId = course.getCourseId();
    this.courseTitle = course.getCourseTitle();
    this.courseDescription = course.getCourseDescription();

  }

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }

  public String getCourseTitle() {
    return courseTitle;
  }

  public void setCourseTitle(String courseTitle) {
    this.courseTitle = courseTitle;
  }

  public String getCourseDescription() {
    return courseDescription;
  }

  public void setCourseDescription(String courseDescription) {
    this.courseDescription = courseDescription;
  }

}
