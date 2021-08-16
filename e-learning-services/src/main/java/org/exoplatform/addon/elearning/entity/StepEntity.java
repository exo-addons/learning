package org.exoplatform.addon.elearning.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;

@Entity
@ExoEntity
@Table(name = "ADDON_E_LEARNING_STEP")
public class StepEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Column(name = "MEDIA")
  private Long media;

  @Column(name = "ORDER")
  private int order;

  public StepEntity() {
  }

  public StepEntity(Long id, Long media) {
    this.id = id;
    this.media = media;
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

  public void setMedia(Long media) {
    this.media = media;
  }

}
