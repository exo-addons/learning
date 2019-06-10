package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@ExoEntity
@Table(name = "ELEARNING_CATEGORY")
public class CategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_CATEGORY")
  private Long         idCategory;
  @Column(name = "NAME_CATEGORY")
  private String       nameCategory;
  @OneToMany(mappedBy="category",fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  private Collection<CourseEntity> courses;

  public CategoryEntity() {
  }

  public CategoryEntity(String nameCategory, String userName) {
    this.nameCategory = nameCategory;
  }

  public Long getIdCategory() {
    return idCategory;
  }

  public void setIdCategory(Long idCategory) {
    this.idCategory = idCategory;
  }

  public String getNameCategory() {
    return nameCategory;
  }

  public void setNameCategory(String nameCategory) {
    this.nameCategory = nameCategory;
  }

}
