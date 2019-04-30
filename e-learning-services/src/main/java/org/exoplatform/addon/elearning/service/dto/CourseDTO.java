package org.exoplatform.addon.elearning.service.dto;

import org.exoplatform.addon.elearning.entities.CourseEntity;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class CourseDTO implements Serializable {
  private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  private Long   idCourse;
  private String   NameCourse;
  private Boolean visibilityCourse;
  private String     dateStart;
  private String     dateEnd;
  private int      nbPerson;
  private String   rewardCourse;
  private Long idCategory;
  private CourseEntity.Status status;
  private byte[] icon;
  private long iconFileId;
  private String uploadId;

  public CourseDTO() {
  }

  public CourseDTO(CourseEntity cours) {
    this.idCourse = cours.getIdCourse();
    NameCourse = cours.getNameCourse();
    this.visibilityCourse = cours.getVisibiltyCourse();
    if (cours.getDateStart()!= null) {
      this.dateStart= formatter.format(cours.getDateStart());
    }
    if (cours.getDateEnd()!= null) {
      this.dateEnd= formatter.format(cours.getDateEnd());
    }
    this.nbPerson = cours.getNbPerson();
    this.rewardCourse = cours.getRewardCourse();
    this.idCategory = cours.getCategory().getIdCategory();
    this.status=cours.getStatus();
    this.iconFileId=cours.getIconFileId();
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

  public CourseEntity.Status getStatus() {
    return status;
  }

  public void setStatus(CourseEntity.Status status) {
    this.status = status;
  }

  public byte[] getIcon() {
    return icon;
  }

  public void setIcon(byte[] icon) {
    this.icon = icon;
  }

  public long getIconFileId() {
    return iconFileId;
  }

  public void setIconFileId(long iconFileId) {
    this.iconFileId = iconFileId;
  }

  public String getUploadId() {
    return uploadId;
  }

  public void setUploadId(String uploadId) {
    this.uploadId = uploadId;
  }
}
