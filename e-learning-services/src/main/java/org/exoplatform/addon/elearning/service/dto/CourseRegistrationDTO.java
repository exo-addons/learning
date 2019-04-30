package org.exoplatform.addon.elearning.service.dto;

import org.exoplatform.addon.elearning.entities.CourseRegistrationEntity;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class CourseRegistrationDTO implements Serializable {
  private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  private Long idRegistration;
  private String nameCourse;
  private String nameWorker;
  private String dateRegistration;

  public CourseRegistrationDTO() {
  }

  public CourseRegistrationDTO(CourseRegistrationEntity courseRegistration) {
    this.idRegistration = courseRegistration.getIdRegistration();
    this.nameCourse = courseRegistration.getCourse().getNameCourse();
    this.nameWorker = courseRegistration.getWorker().getNameWorker();
    if (courseRegistration.getRegisteredAt()!= null) {
      this.dateRegistration= formatter.format(courseRegistration.getRegisteredAt());
    }
  }

  public Long getIdRegistration() {
    return idRegistration;
  }

  public void setIdRegistration(Long idRegistration) {
    this.idRegistration = idRegistration;
  }

  public String getNameCourse() {
    return nameCourse;
  }

  public void setNameCourse(String nameCourse) {
    this.nameCourse = nameCourse;
  }

  public String getNameWorker() {
    return nameWorker;
  }

  public void setNameWorker(String nameWorker) {
    this.nameWorker = nameWorker;
  }

  public String getDateRegistration() {
    return dateRegistration;
  }

  public void setDateRegistration(String dateRegistration) {
    this.dateRegistration = dateRegistration;
  }
}
