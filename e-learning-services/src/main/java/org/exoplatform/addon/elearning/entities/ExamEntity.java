package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@ExoEntity
@Table(name = "ELEARNING_EXAM")
public class ExamEntity {
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
  @Column(name ="USERNAME_EXAM")
  private String userName;

  public ExamEntity() {
  }

  public ExamEntity(String nameExam,
                    Date dateStartExam,
                    Date dateEndExam,
                    Long nbBidExam,
                    String rewardExam,
                    String userName) {
    this.nameExam = nameExam;
    this.dateStartExam = dateStartExam;
    this.dateEndExam = dateEndExam;
    this.nbBidExam = nbBidExam;
    this.rewardExam = rewardExam;
    this.userName = userName;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
