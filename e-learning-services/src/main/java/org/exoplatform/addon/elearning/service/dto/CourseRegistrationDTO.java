package org.exoplatform.addon.elearning.service.dto;

import org.exoplatform.addon.elearning.entities.CourseRegistrationEntity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CourseRegistrationDTO implements Serializable {
  String pattern = "yyyy-mm-dd hh:mm:ss";
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));

  private Long idRegistration;
  private Long idCourse;
  private Long idWorker;
  private String dateRegistration;

  public CourseRegistrationDTO() {
  }

  public CourseRegistrationDTO(CourseRegistrationEntity courseRegistration) {
    this.idRegistration = courseRegistration.getIdRegistration();
    this.idCourse = courseRegistration.getCourse().getIdCourse();
    this.idWorker = courseRegistration.getWorker().getIdWorker();
    if (courseRegistration.getRegisteredAt()!= null) {
      this.dateRegistration= simpleDateFormat.format(courseRegistration.getRegisteredAt());
    }
  }

  public Long getIdRegistration() {
    return idRegistration;
  }

  public void setIdRegistration(Long idRegistration) {
    this.idRegistration = idRegistration;
  }

  public Long getIdCourse() {
    return idCourse;
  }

  public void setIdCourse(Long idCourse) {
    this.idCourse = idCourse;
  }

  public Long getIdWorker() {
    return idWorker;
  }

  public void setIdWorker(Long idWorker) {
    this.idWorker = idWorker;
  }

  public String getDateRegistration() {
    return dateRegistration;
  }

  public void setDateRegistration(String dateRegistration) {
    this.dateRegistration = dateRegistration;
  }
}
