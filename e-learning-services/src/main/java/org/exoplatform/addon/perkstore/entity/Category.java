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
  private Long         idCategory;
  @Column(name = "NAME_CATEGORY")
  private String       nameCategory;
  @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
  private List<Course> course;

  public Category() {
  }

  public Category(String nameCategory, List<Course> course) {
    this.nameCategory = nameCategory;
    this.course = course;
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

  public List<Course> getCourse() {
    return course;
  }

  public void setCourse(List<Course> course) {
    this.course = course;
  }
}
