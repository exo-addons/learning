package org.exoplatform.addon.perkstore.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@ExoEntity
@Table(name = "ELEARNING_COURSE_REGISTRATION")
public class CourseRegistration {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_REGISTRATION")
  Long idRegistration;

  @ManyToOne
  @JoinColumn(name = "WORKER_ID")
  Worker worker;

  @ManyToOne
  @JoinColumn(name = "COURSE_ID")
  Course course;
  @Column(name = "REGISTEREDAT_REGISTRATION")
  Date   registeredAt;
  @Column(name = "LEVEL_REGISTRATION")
  int    level;

  public CourseRegistration() {
  }

  public CourseRegistration(Worker worker, Course course, Date registeredAt, int level) {
    this.worker = worker;
    this.course = course;
    this.registeredAt = registeredAt;
    this.level = level;
  }

  public Long getIdRegistration() {
    return idRegistration;
  }

  public void setIdRegistration(Long idRegistration) {
    this.idRegistration = idRegistration;
  }

  public Worker getWorker() {
    return worker;
  }

  public void setWorker(Worker worker) {
    this.worker = worker;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Date getRegisteredAt() {
    return registeredAt;
  }

  public void setRegisteredAt(Date registeredAt) {
    this.registeredAt = registeredAt;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }
}
