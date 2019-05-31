package org.exoplatform.addon.elearning.service.dto;

import org.exoplatform.addon.elearning.entities.ExamEntity;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class ExamDTO implements Serializable {
  private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  private Long idExam;
  private String nameExam;
  private String dateStartExam;
  private String dateEndExam;
  private Long nbBidExam;
  private String rewardExam;
  private String userName;
  private Long idCourse;

  public ExamDTO() {
  }

  public ExamDTO(ExamEntity exam) {
    this.idExam = exam.getIdExam();
    this.nameExam = exam.getNameExam();
    if (exam.getDateStartExam()!= null) {
      this.dateStartExam= formatter.format(exam.getDateStartExam());
    }
    if (exam.getDateStartExam()!= null) {
      this.dateEndExam = formatter.format(exam.getDateEndExam());
    }
    this.nbBidExam = exam.getNbBidExam();
    this.rewardExam = exam.getRewardExam();
    this.userName=exam.getUserName();
    this.idCourse=exam.getCourse().getIdCourse();
  }

  public Long getIdExam() {
    return idExam;
  }

  public void setIdExam(Long idExam) {
    this.idExam = idExam;
  }

  public String getNameExam() {
    return nameExam;
  }

  public void setNameExam(String nameExam) {
    this.nameExam = nameExam;
  }

  public String getDateStartExam() {
    return dateStartExam;
  }

  public void setDateStartExam(String dateStartExam) {
    this.dateStartExam = dateStartExam;
  }

  public String getDateEndExam() {
    return dateEndExam;
  }

  public void setDateEndExam(String dateEndExam) {
    this.dateEndExam = dateEndExam;
  }

  public Long getNbBidExam() {
    return nbBidExam;
  }

  public void setNbBidExam(Long nbBidExam) {
    this.nbBidExam = nbBidExam;
  }

  public String getRewardExam() {
    return rewardExam;
  }

  public void setRewardExam(String rewardExam) {
    this.rewardExam = rewardExam;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getIdCourse() {
    return idCourse;
  }

  public void setIdCourse(Long idCourse) {
    this.idCourse = idCourse;
  }
}
