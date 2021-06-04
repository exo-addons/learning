package org.exoplatform.addon.elearning.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.exoplatform.commons.api.persistence.ExoEntity;

@Entity
@ExoEntity
@Table(name = "ADDON_E_LEARNING_THEME")
@NamedQueries({ @NamedQuery(name = "ThemeEntity.getAllNames", query = "SELECT t FROM ThemeEntity t") })

public class ThemeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long   id;

  @Column(name = "NAME")
  private String name;

  /*
   * @ManyToOne
   * @JoinColumn(name = "PARENT_THEME") private ThemeEntity themeP;
   * @OneToMany(mappedBy = "themeP", cascade = CascadeType.ALL) private
   * Collection<ThemeEntity> subTheme;
   */

  public ThemeEntity() {
  }

  public ThemeEntity(String name) {
    this.name = name;
  }

  public ThemeEntity(Long id, String name) {
    this.id = id;
    this.name = name;
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

}
