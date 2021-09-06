package org.exoplatform.addon.elearning.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Entity
@ExoEntity
@Table(name = "EXO_E_LEARNING_TUTORIAL")
@NamedQueries({
        @NamedQuery(name = "TutorialEntity.getAllTutorialsByTheme", query = "SELECT t FROM TutorialEntity t INNER JOIN t.themeEntities theme WHERE theme = :id"),
        @NamedQuery(name = "TutorialEntity.findTutorialsByName", query = "SELECT t FROM TutorialEntity t INNER JOIN t.themeEntities theme WHERE theme = :id AND LOWER(t.title) LIKE LOWER(CONCAT('%', :tutoTitle, '%'))")})
public class TutorialEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "TUTORIAL_ID")
  private Long id;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "AUTHOR")
  private String author;

  @Column(name = "CREATED_DATE")
  private Timestamp createdDate;

  @ManyToMany
  @JoinTable(name = "EXO_E_LEARNING_TUTORIAL_THEME", joinColumns = @JoinColumn(name = "TUTORIAL_ID"), inverseJoinColumns = @JoinColumn(name = "THEME_ID"))
  public Set<ThemeEntity> themeEntities;

  @Column(name = "STATUS")
  private String status;

  public TutorialEntity() {
  }

  public TutorialEntity(Long id, String title, String description, String author, Timestamp createdDate, Set<ThemeEntity> themeEntities, String status) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.author = author;
    this.createdDate = createdDate;
    this.themeEntities = themeEntities;
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

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Timestamp getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Timestamp createdDate) {
    this.createdDate = createdDate;
  }

  public Set<ThemeEntity> getThemeEntities() {
    return themeEntities;
  }

  public void setThemeEntities(Set<ThemeEntity> themeEntities) {
    this.themeEntities = themeEntities;
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
