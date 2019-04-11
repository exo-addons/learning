package org.exoplatform.addon.elearning.dto;

import org.exoplatform.addon.elearning.entity.Course;

import java.io.Serializable;

public class CourseDTO implements Serializable {
  private Long   idCourse;
  private String   NameCourse;
  private Boolean visibilityCourse;
  private String     dateStart;
  private String     dateEnd;
  private int      nbPerson;
  private String   rewardCourse;
  private Long idCategory;

  public CourseDTO() {
  }

  public CourseDTO(Course course) {
    this.NameCourse=course.getNameCourse();
    this.idCategory=course.getCategory().getIdCategory();
  }

  public Long getIdCourse() {
    return idCourse;
  }

  public void setIdCourse(Long idCourse) {
    this.idCourse = idCourse;
  }

  public String getNameCourse() {
    return NameCourse;
  }

  public void setNameCourse(String nameCourse) {
    NameCourse = nameCourse;
  }

  public Boolean getVisibilityCourse() {
    return visibilityCourse;
  }

  public void setVisibilityCourse(Boolean visibilityCourse) {
    this.visibilityCourse = visibilityCourse;
  }

  public String getDateStart() {
    return dateStart;
  }

  public void setDateStart(String dateStart) {
    this.dateStart = dateStart;
  }

  public String getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(String dateEnd) {
    this.dateEnd = dateEnd;
  }

  public int getNbPerson() {
    return nbPerson;
  }

  public void setNbPerson(int nbPerson) {
    this.nbPerson = nbPerson;
  }

  public String getRewardCourse() {
    return rewardCourse;
  }

  public void setRewardCourse(String rewardCourse) {
    this.rewardCourse = rewardCourse;
  }

  public Long getIdCategory() {
    return idCategory;
  }

  public void setIdCategory(Long idCategory) {
    this.idCategory = idCategory;
  }
}
