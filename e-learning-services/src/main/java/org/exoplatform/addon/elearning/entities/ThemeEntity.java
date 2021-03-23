package org.exoplatform.addon.elearning.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.exoplatform.commons.api.persistence.ExoEntity;

@Entity
@ExoEntity
@Table(name = "ADDON_E_LEARNING_THEME")
public class ThemeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long                       id;

  @Column(name = "NAME")
  private String                     name;

  @ManyToMany(mappedBy = "theme")
  private Collection<TutorialEntity> tuto;

  /*
   * private Collection<ThemeEntity> subTheme;
   */
  public ThemeEntity() {
  }

  public ThemeEntity(Long id, String name, Collection<TutorialEntity> tuto) {

    this.id = id;
    this.name = name;
    this.tuto = tuto;
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

  public Collection<TutorialEntity> getTuto() {
    return tuto;
  }

  public void setTuto(Collection<TutorialEntity> tuto) {
    this.tuto = tuto;
  }

}
