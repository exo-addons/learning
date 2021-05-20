package org.exoplatform.addon.elearning.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

  @ManyToMany(mappedBy = "theme", cascade = CascadeType.ALL)
  private Collection<TutorialEntity> tuto;

  @ManyToOne
  @JoinColumn(name = "PARENT_THEME")
  private ThemeEntity                themeP;

  @OneToMany(mappedBy = "themeP", cascade = CascadeType.ALL)
  private Collection<ThemeEntity>    subTheme;

  public ThemeEntity() {
  }

  public ThemeEntity(Long id,
                     String name,
                     Collection<TutorialEntity> tuto,
                     ThemeEntity themeP,
                     Collection<ThemeEntity> subTheme) {
    this.id = id;
    this.name = name;
    this.tuto = tuto;
    this.themeP = themeP;
    this.subTheme = subTheme;
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

  public ThemeEntity getThemeP() {
    return themeP;
  }

  public void setThemeP(ThemeEntity themeP) {
    this.themeP = themeP;
  }

  public Collection<ThemeEntity> getSubTheme() {
    return subTheme;
  }

  public void setSubTheme(Collection<ThemeEntity> subTheme) {
    this.subTheme = subTheme;
  }

}
