package org.exoplatform.addon.elearning.entities;

import java.sql.Timestamp;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.exoplatform.commons.api.persistence.ExoEntity;

@Entity
@ExoEntity
@Table(name = "ADDON_E_LEARNING_TUTO")
@NamedQueries({
    @NamedQuery(name = "TutorialEntity.getAllTutosByTheme", query = "SELECT t FROM TutorialEntity t INNER JOIN t.themeIds theme WHERE theme = :id") })
public class TutorialEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long                   id;

  @Column(name = "TITLE")
  private String                 title;

  @Column(name = "DESCRIPTION")
  private String                 description;

  @Column(name = "AUTHOR")
  private String                 author;

  @Column(name = "CREATED_DATE")
  private Timestamp              createdDate;

  @ElementCollection
  @CollectionTable(name = "ADDON_E_LEARNING_TUTO_THEME", joinColumns = @JoinColumn(name = "TUTO_ID"))
  @Column(name = "THEME_ID")
  public Collection<Long>        themeIds;
  
  /*
  @OneToMany(mappedBy = "tuto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Collection<StepEntity> steps;
  */

  @Column(name = "STATUS")
  private String                 status;

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
