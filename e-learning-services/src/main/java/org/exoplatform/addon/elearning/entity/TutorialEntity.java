package org.exoplatform.addon.elearning.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@ExoEntity
@Table(name = "EXO_E_LEARNING_TUTORIAL")
@NamedQueries({
        @NamedQuery(name = "TutorialEntity.getAllTutorialsByTheme", query = "SELECT t FROM TutorialEntity t INNER JOIN t.themeIds theme WHERE theme = :id"),
        @NamedQuery(name = "TutorialEntity.findTutorialsByName", query = "SELECT t FROM TutorialEntity t INNER JOIN t.themeIds theme WHERE theme = :id AND LOWER(t.title) LIKE LOWER(CONCAT('%', :tutoTitle, '%'))")})
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

  @ElementCollection
  @CollectionTable(name = "EXO_E_LEARNING_TUTO_THEME", joinColumns = @JoinColumn(name = "TUTORIAL_ID"))
  @Column(name = "THEME_ID")
  public Collection<Long> themeIds;

  @Column(name = "STATUS")
  private String status;

  public TutorialEntity() {
  }

  public TutorialEntity(Long id,
                        String title,
                        String description,
                        String author,
                        Timestamp createdDate,
                        Collection<Long> themeIds,
                        String status) {

    this.id = id;
    this.title = title;
    this.description = description;
    this.author = author;
    this.createdDate = createdDate;
    this.themeIds = themeIds;
    this.status = status;
  }

  public TutorialEntity(String title,
                        String description,
                        String author,
                        Timestamp createdDate,
                        Collection<Long> themeIds,
                        String status) {
    this.title = title;
    this.description = description;
    this.author = author;
    this.createdDate = createdDate;
    this.themeIds = themeIds;
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

  public Collection<Long> getThemeIds() {
    return themeIds;
  }

  public void setThemeIds(Collection<Long> themeIds) {
    this.themeIds = themeIds;
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
