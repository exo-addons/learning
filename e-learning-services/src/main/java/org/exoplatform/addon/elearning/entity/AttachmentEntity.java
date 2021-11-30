package org.exoplatform.addon.elearning.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@ExoEntity
@Table(name = "EXO_E_LEARNING_ATTACHMENT")
public class AttachmentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ATTACHMENT_ID")
  private Long id;

  @Column(name = "FULL_TITLE")
  private String fullTitle;

  @Column(name = "CREATED_DATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @Column(name = "ATTACHMENT_FILE_ID")
  private Long attachmentFileID;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "STEP_ID")
  private StepEntity stepEntity;

  public AttachmentEntity() {
  }

  public AttachmentEntity(Long id, Date createdDate, String fullTitle, Long attachmentFileID, StepEntity stepEntity) {
    this.id = id;
    this.createdDate = createdDate;
    this.fullTitle = fullTitle;
    this.attachmentFileID = attachmentFileID;
    this.stepEntity = stepEntity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getFullTitle() {
    return fullTitle;
  }

  public void setFullTitle(String fullTitle) {
    this.fullTitle = fullTitle;
  }

  public Long getAttachmentFileID() {
    return attachmentFileID;
  }

  public void setAttachmentFileID(Long attachmentFileID) {
    this.attachmentFileID = attachmentFileID;
  }

  public StepEntity getStepEntity() {
    return stepEntity;
  }

  public void setStepEntity(StepEntity stepEntity) {
    this.stepEntity = stepEntity;
  }
}
