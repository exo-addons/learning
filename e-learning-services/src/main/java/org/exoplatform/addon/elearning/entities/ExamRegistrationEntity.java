package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@ExoEntity
@Table(name = "ELEARNING_EXAM_REGISTRATION")
public class ExamRegistrationEntity {
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
  WorkerEntity workerx;

  @ManyToOne
  @JoinColumn(name = "EXAM_ID")
  ExamEntity exam;

  public ExamRegistrationEntity() {
  }

  public ExamRegistrationEntity(Date registeredExam, int markExam, WorkerEntity workerx, ExamEntity exam) {
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

  public WorkerEntity getWorkerx() {
    return workerx;
  }

  public void setWorkerx(WorkerEntity workerx) {
    this.workerx = workerx;
  }

  public ExamEntity getExam() {
    return exam;
  }

  public void setExam(ExamEntity exam) {
    this.exam = exam;
  }
}
