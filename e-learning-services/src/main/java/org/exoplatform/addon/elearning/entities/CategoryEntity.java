package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;

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
  @Column(name ="USERNAME_CATEGORY")
  private String userName;

  public CategoryEntity() {
  }

  public CategoryEntity(String nameCategory, String userName) {
    this.nameCategory = nameCategory;
    this.userName = userName;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
