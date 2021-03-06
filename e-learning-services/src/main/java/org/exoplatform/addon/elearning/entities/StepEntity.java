package org.exoplatform.addon.elearning.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.exoplatform.commons.api.persistence.ExoEntity;

@Entity
@ExoEntity
@Table(name = "ADDON_E_LEARNING_STEP")
public class StepEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long           id;

  /*
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tuto_id")
  private TutorialEntity tuto;
  */
  
  @Column(name = "MEDIA")
  private Long           media;

  @Column(name = "ORDER")
  private int            order;

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
