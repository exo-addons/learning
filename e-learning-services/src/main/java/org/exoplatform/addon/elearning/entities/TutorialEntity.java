package org.exoplatform.addon.elearning.entities;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.google.api.client.util.DateTime;

import org.exoplatform.commons.api.persistence.ExoEntity;

@Entity
@ExoEntity
@Table(name = "ADDON_E_LEARNING_TUTO")
public class TutorialEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long                    id;

  @Column(name = "TITLE")
  private String                  title;

  @Column(name = "DESCRIPTION")
  private String                  description;

  @Column(name = "AUTHOR_ID")
  private Long                    authorId; //TODO convert it to String to save the username

  @Column(name = "CREATED_DATE")
  private Timestamp               createdDate;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "ADDON_E_LEARNING_TUTO_THEME", joinColumns = @JoinColumn(name = "tuto_id"), inverseJoinColumns = @JoinColumn(name = "theme_id"))
  private Collection<ThemeEntity> theme;

  @OneToMany(mappedBy = "tuto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Collection<StepEntity>  steps;

  @Column(name = "STATUS")
  private String                  status = "DRAFT";

  public TutorialEntity() {
  }

  public TutorialEntity(Long id,
                        String title,
                        String description,
                        Long authorId,
                        Timestamp createdDate,
                        Collection<ThemeEntity> theme,
                        Collection<StepEntity> steps,
                        String status) {

    this.id = id;
    this.title = title;
    this.description = description;
    this.authorId = authorId;
    this.createdDate = createdDate;
    this.theme = theme;
    this.steps = steps;
    this.status = status;
  }

  public TutorialEntity(String title, String description, Long authorId, Timestamp createdDate, String status) {
    this.title = title;
    this.description = description;
    this.authorId = authorId;
    this.createdDate = createdDate;
    this.status = status;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public Timestamp getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Timestamp createdDate) {
    this.createdDate = createdDate;
  }

  public Collection<ThemeEntity> getTheme() {
    return theme;
  }

  public void setTheme(Collection<ThemeEntity> theme) {
    this.theme = theme;
  }

  public Collection<StepEntity> getSteps() {
    return steps;
  }

  public void setSteps(Collection<StepEntity> steps) {
    this.steps = steps;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public enum Status {
    DRAFT, ARCHIVED, PUBLISHED
  }

}
