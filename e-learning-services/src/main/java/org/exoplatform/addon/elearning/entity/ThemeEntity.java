package org.exoplatform.addon.elearning.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@ExoEntity
@Table(name = "EXO_E_LEARNING_THEME")
@NamedQueries({
        @NamedQuery(
                name = "ThemeEntity.findAllThemesByName",
                query = "SELECT t FROM ThemeEntity t where LOWER(t.name) LIKE LOWER(CONCAT('%', :themeName, '%'))"
        ),
        @NamedQuery(
                name = "ThemeEntity.countParentThemeChildren",
                query = "SELECT count (t) FROM ThemeEntity t where t.parent.id = :id"
        ),
        @NamedQuery(
                name = "ThemeEntity.retrieveChildThemes",
                query = "SELECT t FROM ThemeEntity t where t.parent.id = :id"
        ),
        @NamedQuery(
                name = "ThemeEntity.countSearchedParentThemeChildren",
                query = "SELECT count (t) FROM ThemeEntity t where t.parent.id = :id AND LOWER(t.name) LIKE LOWER(CONCAT('%', :themeName, '%'))"
        ),
        @NamedQuery(
                name = "ThemeEntity.retrieveSearchedChildThemes",
                query = "SELECT t FROM ThemeEntity t where t.parent.id = :id AND LOWER(t.name) LIKE LOWER(CONCAT('%', :themeName, '%'))"
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
  private Set<String> managers = new HashSet<>();

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "EXO_E_LEARNING_THEME_PARTICIPATORS",
          joinColumns = @JoinColumn(name = "THEME_ID"))
  @Column(name = "PARTICIPATORS")
  private Set<String> participators = new HashSet<>();

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "PARENT_THEME_ID")
  private ThemeEntity parent;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Set<ThemeEntity> children = new HashSet<>();

  @Column(name = "LAST_MODIFIED_DATE")
  private Long lastModifiedDate;

  @ManyToMany(mappedBy = "themeEntities", fetch = FetchType.LAZY)
  @Column(name = "TUTORIAL_ID")
  private Set<TutorialEntity> tutorialEntities = new HashSet<>();

  @Column(name = "CREATOR")
  private String creator;

  public ThemeEntity() {
  }

  public ThemeEntity(Long id, String name, String spaceName, Set<String> managers, Set<String> participators, ThemeEntity parent, Set<ThemeEntity> children,
                     Long lastModifiedDate, Set<TutorialEntity> tutorialEntities, String creator) {
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

  public Set<String> getManagers() {
    return managers;
  }

  public void setManagers(Set<String> managers) {
    this.managers = managers;
  }

  public Set<String> getParticipators() {
    return participators;
  }

  public void setParticipators(Set<String> participators) {
    this.participators = participators;
  }

  public ThemeEntity getParent() {
    return parent;
  }

  public void setParent(ThemeEntity parent) {
    this.parent = parent;
  }

  public Set<ThemeEntity> getChildren() {
    return children;
  }

  public void setChildren(Set<ThemeEntity> children) {
    this.children = children;
  }

  public Long getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Long lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public Set<TutorialEntity> getTutorialEntities() {
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
