package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;

@Entity(name="cregistration")
@ExoEntity
@Table(name = "ELEARNING_COURSE_REGISTRATION")
@NamedQueries({
    @NamedQuery(name = "cregistration.findRegitrationByIdWorker", query = "select c from cregistration c where c.worker.nameWorker=:name"),
    @NamedQuery(name = "cregistration.getCountWorker", query = "SELECT count(*) FROM cregistration c where c.worker.idWorker=:id")
})
public class CourseRegistrationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_REGISTRATION")
  Long idRegistration;

  @ManyToOne
  @JoinColumn(name = "WORKER_ID")
  WorkerEntity worker;

  @ManyToOne
  @JoinColumn(name = "COURSE_ID")
  CourseEntity course;
  @Column(name = "REGISTEREDAT_REGISTRATION")
  Date         registeredAt;


  public CourseRegistrationEntity() {
  }

  public CourseRegistrationEntity(WorkerEntity worker, CourseEntity course, Date registeredAt, int level) {
    this.worker = worker;
    this.course = course;
    this.registeredAt = registeredAt;
  }

  public Long getIdRegistration() {
    return idRegistration;
  }

  public void setIdRegistration(Long idRegistration) {
    this.idRegistration = idRegistration;
  }

  public WorkerEntity getWorker() {
    return worker;
  }

  public void setWorker(WorkerEntity worker) {
    this.worker = worker;
  }

  public CourseEntity getCourse() {
    return course;
  }

  public void setCourse(CourseEntity course) {
    this.course = course;
  }

  public Date getRegisteredAt() {
    return registeredAt;
  }

  public void setRegisteredAt(Date registeredAt) {
    this.registeredAt = registeredAt;
  }

}
