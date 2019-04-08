package org.exoplatform.addon.perkstore.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@ExoEntity
@Table(name = "ELEARNING_EXAM")
public class Exam {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_EXAM")
  private Long           idExam;
  @Column(name ="NAME_EXAM")
  private String         nameExam;
  @Column(name ="DATE_START_EXAM")
  private Date           dateStartExam;
  @Column(name ="DATE_END_EXAM")
  private Date           dateEndExam;
  @Column(name ="NB_BID_EXAM")
  private Long           nbBidExam;
  @Column(name = "REWARD_EXAM")
  private String         rewardExam;
  @OneToMany(mappedBy = "exam")
  private List<Exercise> exercises;

  @OneToMany(cascade=CascadeType.ALL,mappedBy = "exam")
  private List<ExamRegistration> examregistrations;

  public Exam() {
  }

  public Exam(String nameExam,
              Date dateStartExam,
              Date dateEndExam,
              Long nbBidExam,
              String rewardExam,
              List<Exercise> exercises, List<ExamRegistration> examregistrations) {
    this.nameExam = nameExam;
    this.dateStartExam = dateStartExam;
    this.dateEndExam = dateEndExam;
    this.nbBidExam = nbBidExam;
    this.rewardExam = rewardExam;
    this.exercises = exercises;
    this.examregistrations = examregistrations;
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

  public Date getDateStartExam() {
    return dateStartExam;
  }

  public void setDateStartExam(Date dateStartExam) {
    this.dateStartExam = dateStartExam;
  }

  public Date getDateEndExam() {
    return dateEndExam;
  }

  public void setDateEndExam(Date dateEndExam) {
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

  public List<Exercise> getExercises() {
    return exercises;
  }

  public void setExercises(List<Exercise> exercises) {
    this.exercises = exercises;
  }

  public List<ExamRegistration> getExamregistrations() {
    return examregistrations;
  }

  public void setExamregistrations(List<ExamRegistration> examregistrations) {
    this.examregistrations = examregistrations;
  }
}
