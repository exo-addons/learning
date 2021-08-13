package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;

@Entity
@ExoEntity
@Table(name = "ADDON_E_LEARNING_THEME")
@NamedQueries({
        @NamedQuery(
                name = "ThemeEntity.findAllThemesByName",
                query = "SELECT t FROM ThemeEntity t where LOWER(t.name) LIKE LOWER(CONCAT('%', :themeName, '%'))"
        ),})

public class ThemeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "SPACE_NAME")
  private String spaceName;

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

  public ThemeEntity(Long id, String name, String spaceName) {
    this.id = id;
    this.name = name;
    this.spaceName = spaceName;
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


}
