package org.exoplatform.addon.elearning.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ExoEntity
@Table(name = "EXO_E_LEARNING_STEP")
public class StepEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "STEP_ID")
  private Long id;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "CONTENT", length = 2000)
  private String content;

  @Column(name = "IMAGE_FILE_ID")
  private Long imageFileId;

  @Column(name = "MEDIA_LINK", length = 2000)
  private String mediaLink;

  @Column(name = "ORDER")
  private int order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TUTORIAL_ID", nullable = false)
  private TutorialEntity tutorialEntity;

  @OneToMany(mappedBy = "stepEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<AttachmentEntity> attachmentEntities = new ArrayList<>();

  public StepEntity() {
  }

  public StepEntity(Long id, String title, String content, Long imageFileId, String mediaLink, int order, TutorialEntity tutorialEntity, List<AttachmentEntity> attachmentEntities) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.imageFileId = imageFileId;
    this.mediaLink = mediaLink;
    this.order = order;
    this.tutorialEntity = tutorialEntity;
    this.attachmentEntities = attachmentEntities;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Long getImageFileId() {
    return imageFileId;
  }

  public void setImageFileId(Long imageFileId) {
    this.imageFileId = imageFileId;
  }

  public String getMediaLink() {
    return mediaLink;
  }

  public void setMediaLink(String mediaLink) {
    this.mediaLink = mediaLink;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public TutorialEntity getTutorialEntity() {
    return tutorialEntity;
  }

  public void setTutorialEntity(TutorialEntity tutorialEntity) {
    this.tutorialEntity = tutorialEntity;
  }

  public List<AttachmentEntity> getAttachmentEntities() {
    return attachmentEntities;
  }

  public void setAttachmentEntities(List<AttachmentEntity> attachmentEntities) {
    this.attachmentEntities = attachmentEntities;
  }
}
