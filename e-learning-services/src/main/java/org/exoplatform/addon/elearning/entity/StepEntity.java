package org.exoplatform.addon.elearning.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;

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

  @Column(name = "BODY", length = 2000)
  private String body;

  @Column(name = "MEDIA")
  private Long media;

  @Column(name = "ORDER")
  private int order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TUTORIAL_ID", nullable = false)
  @Column(name = "TUTORIAL_ID")
  private TutorialEntity tutorialEntity;

  public StepEntity() {
  }

  public StepEntity(Long id, String title, String body, Long media, int order, TutorialEntity tutorialEntity) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.media = media;
    this.order = order;
    this.tutorialEntity = tutorialEntity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getMedia() {
    return media;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public void setMedia(Long media) {
    this.media = media;
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
}
