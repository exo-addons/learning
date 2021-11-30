package org.exoplatform.addon.elearning.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@ExoEntity
@Table(name = "EXO_E_LEARNING_TUTORIAL")
@NamedQueries({
        @NamedQuery(name = "TutorialEntity.getTutorialsByTheme", query = "SELECT t FROM TutorialEntity t INNER JOIN t.themeEntities theme WHERE theme.id = :themeId order by t.lastModifiedDate, t.createdDate DESC "),
        @NamedQuery(name = "TutorialEntity.countTutorialsByTheme", query = "SELECT count (t) FROM TutorialEntity t INNER JOIN t.themeEntities theme WHERE theme.id = :themeId"),
        @NamedQuery(name = "TutorialEntity.findTutorialsByName", query = "SELECT t FROM TutorialEntity t INNER JOIN t.themeEntities theme WHERE theme = :id AND LOWER(t.title) LIKE LOWER(CONCAT('%', :tutorialTitle, '%')) order by t.lastModifiedDate, t.createdDate DESC "
        )})
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

  @Column(name = "LAST_MODIFIED_DATE")
  private Long lastModifiedDate;

  @ManyToMany
  @JoinTable(
          name = "EXO_E_LEARNING_TUTORIAL_THEME",
          joinColumns = @JoinColumn(name = "TUTORIAL_ID", referencedColumnName = "TUTORIAL_ID"),
          inverseJoinColumns = @JoinColumn(name = "THEME_ID", referencedColumnName = "THEME_ID"))
  @Column(name = "THEME_ID")
  public List<ThemeEntity> themeEntities = new ArrayList<>();

  @Column(name = "STATUS")
  @Enumerated(EnumType.ORDINAL)
  private Status status;

  @OneToMany(mappedBy = "tutorial", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<StepEntity> stepEntities = new ArrayList<>();

  public TutorialEntity() {
  }

  public TutorialEntity(Long id, String title, String description, String author, Timestamp createdDate, List<ThemeEntity> themeEntities, Status status, List<StepEntity> stepEntities) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.author = author;
    this.createdDate = createdDate;
    this.themeEntities = themeEntities;
    this.status = status;
    this.stepEntities = stepEntities;
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

  public Long getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Long lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public List<ThemeEntity> getThemeEntities() {
    return themeEntities;
  }

  public void addThemeEntity(ThemeEntity themeEntity) {
    this.themeEntities.add(themeEntity);
    themeEntity.getTutorialEntities().add(this);
  }

  public void removeThemeEntity(ThemeEntity themeEntity) {
    this.themeEntities.remove(themeEntity);
    themeEntity.getTutorialEntities().remove(this);
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public List<StepEntity> getStepEntities() {
    return stepEntities;
  }

  public void setStepEntities(List<StepEntity> stepEntities) {
    this.stepEntities = stepEntities;
  }

}
