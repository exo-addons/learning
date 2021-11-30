package org.exoplatform.addon.elearning.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ExoEntity
@Table(name = "EXO_E_LEARNING_STEP")
@NamedQueries({
        @NamedQuery(
                name = "StepEntity.findStepByOrder",
                query = "SELECT s FROM StepEntity s where s.tutorial.id = :tutorialId AND s.order = :stepOrder "
        ),})
public class StepEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "STEP_ID")
  private Long id;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "CONTENT")
  private String content;

  @Column(name = "IMAGE_FILE_ID")
  private Long imageFileId;

  @Column(name = "MEDIA_LINK", length = 2000)
  private String mediaLink;

  @Column(name = "STEP_ORDER")
  private int order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TUTORIAL_ID", nullable = false)
  private TutorialEntity tutorial;

  @OneToMany(mappedBy = "stepEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<AttachmentEntity> attachmentEntities = new ArrayList<>();

  public StepEntity() {
  }

  public StepEntity(Long id, String title, String content, Long imageFileId, String mediaLink, int order, TutorialEntity tutorial, List<AttachmentEntity> attachmentEntities) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.imageFileId = imageFileId;
    this.mediaLink = mediaLink;
    this.order = order;
    this.tutorial = tutorial;
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

  public TutorialEntity getTutorial() {
    return tutorial;
  }

  public void setTutorial(TutorialEntity tutorial) {
    this.tutorial = tutorial;
  }

  public List<AttachmentEntity> getAttachmentEntities() {
    return attachmentEntities;
  }

  public void setAttachmentEntities(List<AttachmentEntity> attachmentEntities) {
    this.attachmentEntities = attachmentEntities;
  }
}
