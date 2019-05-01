package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@ExoEntity
@Table(name = "ELEARNING_WORKER")
public class WorkerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_WORKER")
  private Long                                 idWorker;
  @Column(name ="NAME_WORKER")
  private String                               nameWorker;
  @OneToMany(mappedBy="worker",fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  private Collection<CourseRegistrationEntity> coursesRegistrations;
  @OneToMany(mappedBy="exam",fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  private Collection<ExamRegistrationEntity> examsRegistrations;

  public WorkerEntity() {
  }

  public WorkerEntity(String nameWorker) {
    this.nameWorker = nameWorker;
  }

  public Long getIdWorker() {
    return idWorker;
  }

  public void setIdWorker(Long idWorker) {
    this.idWorker = idWorker;
  }

  public String getNameWorker() {
    return nameWorker;
  }

  public void setNameWorker(String nameWorker) {
    this.nameWorker = nameWorker;
  }
}
