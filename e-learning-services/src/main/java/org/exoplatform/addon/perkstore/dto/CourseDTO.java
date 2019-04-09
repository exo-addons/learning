package org.exoplatform.addon.perkstore.dto;

import org.exoplatform.addon.perkstore.entity.Category;
import org.exoplatform.addon.perkstore.entity.Course;

import java.io.Serializable;
import java.util.Date;

public class CourseDTO implements Serializable {
  private Long   idCourse;
  private String   NameCourse;
  private Boolean  visibilityCourse;
  private Date     dateStart;
  private Date     dateEnd;
  private int      nbPerson;
  private String   rewardCourse;
  private Category category;
  //private List<CourseRegistrationDTO> courseRegistrations;

  public CourseDTO() {
  }

  public CourseDTO(Course c ) {
    this.idCourse=c.getIdCourse();
    this.NameCourse = c.getNameCourse();
    this.visibilityCourse = c.getVisibiltyCourse();
    this.dateStart = c.getDateStart();
    this.dateEnd = c.getDateEnd();
    this.nbPerson = c.getNbPerson();
    this.rewardCourse = c.getRewardCourse();
    this.category = c.getCategory();
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

  public Date getDateStart() {
    return dateStart;
  }

  public void setDateStart(Date dateStart) {
    this.dateStart = dateStart;
  }

  public Date getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(Date dateEnd) {
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

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
}
