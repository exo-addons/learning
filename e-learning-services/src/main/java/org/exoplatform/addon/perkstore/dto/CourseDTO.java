package org.exoplatform.addon.perkstore.dto;

import org.exoplatform.addon.perkstore.entity.Category;
import org.exoplatform.addon.perkstore.entity.Course;

import java.io.Serializable;
import java.util.Date;

public class CourseDTO implements Serializable {
  private Long   idCourse;
  private String   NameCourse;
  private int visibilityCourse;
  private String     dateStart;
  private String     dateEnd;
  private int      nbPerson;
  private String   rewardCourse;
  private Long idCategory;

  public CourseDTO() {
  }

  public CourseDTO(String nameCourse,
                   int visibilityCourse,
                   String dateStart,
                   String dateEnd,
                   int nbPerson,
                   String rewardCourse,
                   Long idCategory) {
    NameCourse = nameCourse;
    this.visibilityCourse = visibilityCourse;
    this.dateStart = dateStart;
    this.dateEnd = dateEnd;
    this.nbPerson = nbPerson;
    this.rewardCourse = rewardCourse;
    this.idCategory = idCategory;
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

  public int getVisibilityCourse() {
    return visibilityCourse;
  }

  public void setVisibilityCourse(int visibilityCourse) {
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
