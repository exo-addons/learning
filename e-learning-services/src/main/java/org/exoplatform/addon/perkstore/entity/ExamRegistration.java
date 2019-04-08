package org.exoplatform.addon.perkstore.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@ExoEntity
@Table(name = "ELEARNING_EXAM_REGISTRATION")
public class ExamRegistration {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_EXAM")
  Long idExam;

  @Column(name = "EXAMDAT_REGISTRATION")
  Date registeredExam;
  @Column(name = "MARK_EXAM")
  int markExam;

  @ManyToOne
  @JoinColumn(name = "WORKER_ID")
  Worker workerx;

  @ManyToOne
  @JoinColumn(name = "EXAM_ID")
  Exam exam;

  public ExamRegistration() {
  }

  public ExamRegistration(Date registeredExam, int markExam, Worker workerx, Exam exam) {
    this.registeredExam = registeredExam;
    this.markExam = markExam;
    this.workerx = workerx;
    this.exam = exam;
  }

  public Long getIdExam() {
    return idExam;
  }

  public void setIdExam(Long idExam) {
    this.idExam = idExam;
  }

  public Date getRegisteredExam() {
    return registeredExam;
  }

  public void setRegisteredExam(Date registeredExam) {
    this.registeredExam = registeredExam;
  }

  public int getMarkExam() {
    return markExam;
  }

  public void setMarkExam(int markExam) {
    this.markExam = markExam;
  }

  public Worker getWorkerx() {
    return workerx;
  }

  public void setWorkerx(Worker workerx) {
    this.workerx = workerx;
  }

  public Exam getExam() {
    return exam;
  }

  public void setExam(Exam exam) {
    this.exam = exam;
  }
}
