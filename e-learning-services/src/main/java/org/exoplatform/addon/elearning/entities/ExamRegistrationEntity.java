package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;

@Entity(name="eregistration")
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
  WorkerEntity worker;

  @ManyToOne
  @JoinColumn(name = "EXAM_ID")
  ExamEntity exam;

  public ExamRegistrationEntity() {
  }

  public ExamRegistrationEntity(Date registeredExam, int markExam, WorkerEntity worker, ExamEntity exam) {
    this.registeredExam = registeredExam;
    this.markExam = markExam;
    this.worker = worker;
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

  public WorkerEntity getWorker() {
    return worker;
  }

  public void setWorker(WorkerEntity worker) {
    this.worker= worker;
  }

  public ExamEntity getExam() {
    return exam;
  }

  public void setExam(ExamEntity exam) {
    this.exam = exam;
  }
}
