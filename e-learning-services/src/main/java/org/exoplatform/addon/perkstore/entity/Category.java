package org.exoplatform.addon.perkstore.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@ExoEntity
@Table(name = "ELEARNING_CATEGORY")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_CATEGORY")
  private Long        idCategory;
  @Column(name = "LIBELLE_CATEGORY")
  private String      libelleCategory;
  @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
  private List<Cours> cours;

  public Category() {
  }

  public Category(Long idCategory, String libelleCategory, List<Cours> cours) {
    this.idCategory = idCategory;
    this.libelleCategory = libelleCategory;
    this.cours = cours;
  }

  public Long getIdCategory() {
    return idCategory;
  }

  public void setIdCategory(Long idCategory) {
    this.idCategory = idCategory;
  }

  public String getLibelleCategory() {
    return libelleCategory;
  }

  public void setLibelleCategory(String libelleCategory) {
    this.libelleCategory = libelleCategory;
  }

  public List<Cours> getCours() {
    return cours;
  }

  public void setCours(List<Cours> cours) {
    this.cours = cours;
  }
}
