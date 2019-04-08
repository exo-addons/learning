package org.exoplatform.addon.perkstore.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@ExoEntity
@Table(name = "ELEARNING_WORKER")
public class Worker {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_WORKER")
  private Long                     idWorker;
  @Column(name ="NAME_WORKER")
  private String                   nomWorker;
  @Column(name ="POST_WORKER")
  private String                   postWorker;
  @Column(name ="SKILLS_WORKER")
  private String                   skillsWorker;
  @Column(name ="STATUS_WORKER")
  private Boolean                  statusWorker;//connecté ou non
  @OneToMany(cascade=CascadeType.ALL,mappedBy = "worker")
  private List<CourseRegistration> registrations;

  @OneToMany(cascade=CascadeType.ALL,mappedBy = "workerx")
  private List<ExamRegistration> examregistrations;

  @OneToOne(cascade=CascadeType.ALL)
  @JoinColumn(name = "TYPE_PROFILE_ID",referencedColumnName = "ID_TYPE_PROFILE")
  private TypeProfile typeProfile;

  public Worker() {
  }

  public Worker(String nomWorker,
                String postWorker,
                String skillsWorker,
                Boolean statusWorker,
                List<CourseRegistration> registrations,
                List<ExamRegistration> examregistrations, TypeProfile typeProfile) {
    this.nomWorker = nomWorker;
    this.postWorker = postWorker;
    this.skillsWorker = skillsWorker;
    this.statusWorker = statusWorker;
    this.registrations = registrations;
    this.examregistrations = examregistrations;
    this.typeProfile = typeProfile;
  }

  public Long getIdWorker() {
    return idWorker;
  }

  public void setIdWorker(Long idWorker) {
    this.idWorker = idWorker;
  }

  public String getNomWorker() {
    return nomWorker;
  }

  public void setNomWorker(String nomWorker) {
    this.nomWorker = nomWorker;
  }

  public String getPostWorker() {
    return postWorker;
  }

  public void setPostWorker(String postWorker) {
    this.postWorker = postWorker;
  }

  public String getSkillsWorker() {
    return skillsWorker;
  }

  public void setSkillsWorker(String skillsWorker) {
    this.skillsWorker = skillsWorker;
  }

  public Boolean getStatusWorker() {
    return statusWorker;
  }

  public void setStatusWorker(Boolean statusWorker) {
    this.statusWorker = statusWorker;
  }

  public List<CourseRegistration> getRegistrations() {
    return registrations;
  }

  public void setRegistrations(List<CourseRegistration> registrations) {
    this.registrations = registrations;
  }

  public List<ExamRegistration> getExamregistrations() {
    return examregistrations;
  }

  public void setExamregistrations(List<ExamRegistration> examregistrations) {
    this.examregistrations = examregistrations;
  }

  public TypeProfile getTypeProfile() {
    return typeProfile;
  }

  public void setTypeProfile(TypeProfile typeProfile) {
    this.typeProfile = typeProfile;
  }
}
