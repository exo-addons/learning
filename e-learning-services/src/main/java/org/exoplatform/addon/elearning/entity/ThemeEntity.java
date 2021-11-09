package org.exoplatform.addon.elearning.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ExoEntity
@Table(name = "EXO_E_LEARNING_THEME")
@NamedQueries({
        @NamedQuery(
                name = "ThemeEntity.findAllThemesByName",
                query = "SELECT t FROM ThemeEntity t where LOWER(t.name) LIKE LOWER(CONCAT('%', :themeName, '%')) order by t.lastModifiedDate DESC "
        ),
        @NamedQuery(
                name = "ThemeEntity.countParentThemeChildren",
                query = "SELECT count (t) FROM ThemeEntity t where t.parent.id = :id"
        ),
        @NamedQuery(
                name = "ThemeEntity.retrieveChildThemes",
                query = "SELECT t FROM ThemeEntity t where t.parent.id = :id order by t.lastModifiedDate DESC "
        ),
        @NamedQuery(
                name = "ThemeEntity.countSearchedParentThemeChildren",
                query = "SELECT count (t) FROM ThemeEntity t where t.parent.id = :id AND LOWER(t.name) LIKE LOWER(CONCAT('%', :themeName, '%'))"
        ),
        @NamedQuery(
                name = "ThemeEntity.retrieveSearchedChildThemes",
                query = "SELECT t FROM ThemeEntity t where t.parent.id = :id AND LOWER(t.name) LIKE LOWER(CONCAT('%', :themeName, '%')) order by t.lastModifiedDate DESC "
        ),
        @NamedQuery(
                name = "ThemeEntity.findThemesByMemberships",
                query = "SELECT DISTINCT t FROM ThemeEntity t"
                        + " LEFT JOIN t.managers managers "
                        + " LEFT JOIN t.participators participators "
                        + " WHERE managers IN (:memberships) OR participators IN (:memberships)"
                        + " order by t.lastModifiedDate desc"
        ),})

public class ThemeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "THEME_ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "SPACE_NAME")
  private String spaceName;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "EXO_E_LEARNING_THEME_MANAGERS",
          joinColumns = @JoinColumn(name = "THEME_ID"))
  @Column(name = "MANAGERS")
  private List<String> managers = new ArrayList<>();

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "EXO_E_LEARNING_THEME_PARTICIPATORS",
          joinColumns = @JoinColumn(name = "THEME_ID"))
  @Column(name = "PARTICIPATORS")
  private List<String> participators = new ArrayList<>();

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "PARENT_THEME_ID")
  private ThemeEntity parent;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<ThemeEntity> children = new ArrayList<>();

  @Column(name = "LAST_MODIFIED_DATE")
  private Long lastModifiedDate;

  @ManyToMany(mappedBy = "themeEntities", fetch = FetchType.LAZY)
  @Column(name = "TUTORIAL_ID")
  private List<TutorialEntity> tutorialEntities = new ArrayList<>();

  @Column(name = "CREATOR")
  private String creator;

  public ThemeEntity() {
  }

  public ThemeEntity(Long id, String name, String spaceName, List<String> managers, List<String> participators, ThemeEntity parent, List<ThemeEntity> children,
                     Long lastModifiedDate, List<TutorialEntity> tutorialEntities, String creator) {
    this.id = id;
    this.name = name;
    this.spaceName = spaceName;
    this.managers = managers;
    this.participators = participators;
    this.parent = parent;
    this.children = children;
    this.lastModifiedDate = lastModifiedDate;
    this.tutorialEntities = tutorialEntities;
    this.creator = creator;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSpaceName() {
    return spaceName;
  }

  public void setSpaceName(String spaceName) {
    this.spaceName = spaceName;
  }

  public List<String> getManagers() {
    return managers;
  }

  public void setManagers(List<String> managers) {
    this.managers = managers;
  }

  public List<String> getParticipators() {
    return participators;
  }

  public void setParticipators(List<String> participators) {
    this.participators = participators;
  }

  public ThemeEntity getParent() {
    return parent;
  }

  public void setParent(ThemeEntity parent) {
    this.parent = parent;
  }

  public List<ThemeEntity> getChildren() {
    return children;
  }

  public void setChildren(List<ThemeEntity> children) {
    this.children = children;
  }

  public Long getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Long lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public List<TutorialEntity> getTutorialEntities() {
    return tutorialEntities;
  }

  public void addTutorialEntity(TutorialEntity tutorialEntity) {
    tutorialEntities.add(tutorialEntity);
    tutorialEntity.getThemeEntities().add(this);
  }

  public void removeTutorialEntity(TutorialEntity tutorialEntity) {
    tutorialEntities.remove(tutorialEntity);
    tutorialEntity.getThemeEntities().remove(this);
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }
}
