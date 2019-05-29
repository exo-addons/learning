package org.exoplatform.addon.elearning.service.dto;

import org.exoplatform.addon.elearning.entities.ExamRegistrationEntity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ExamRegistrationDTO implements Serializable {
  String           pattern          = "yyyy-mm-dd hh:mm:ss";
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));

  private Long idRegistration;
  private Long idExam;
  private Long idWorker;
  private String dateRegistration;
  private int mark;
  private String userName;


  public ExamRegistrationDTO() {
  }

  public ExamRegistrationDTO(ExamRegistrationEntity examRegistration) {
    this.idRegistration = examRegistration.getIdExam();
    this.idExam = examRegistration.getExam().getIdExam();
    this.idWorker = examRegistration.getWorker().getIdWorker();
    if (examRegistration.getRegisteredExam()!= null) {
      this.dateRegistration= simpleDateFormat.format(examRegistration.getRegisteredExam());
    }
    this.mark=examRegistration.getMarkExam();
    this.userName=examRegistration.getUserName();
  }

  public Long getIdRegistration() {
    return idRegistration;
  }

  public void setIdRegistration(Long idRegistration) {
    this.idRegistration = idRegistration;
  }

  public Long getIdExam() {
    return idExam;
  }

  public void setIdExam(Long idExam) {
    this.idExam = idExam;
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

  public int getMark() {
    return mark;
  }

  public void setMark(int mark) {
    this.mark = mark;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
