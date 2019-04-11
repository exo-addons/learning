package org.exoplatform.addon.elearning.entity;

/*
 this class define with any space will use connect:"Espace Apprenant" or "Espace Editeur"
 I use it to avoid Tight coupling
 */

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;

@Entity
@ExoEntity
@Table(name = "ELEARNING_TYPE_PROFILE")
public class TypeProfile {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_TYPE_PROFILE")
  private Long   idTypeCompte;
  @Column(name = "NAME_TYPE_PROFILE")
  private String nameTypeProfile;
  @OneToOne(mappedBy = "typeProfile")
  private Worker worker;

  public TypeProfile() {
  }

  public TypeProfile(String nameTypeProfile, Worker worker) {
    this.nameTypeProfile = nameTypeProfile;
    this.worker = worker;
  }

  public Long getIdTypeCompte() {
    return idTypeCompte;
  }

  public void setIdTypeCompte(Long idTypeCompte) {
    this.idTypeCompte = idTypeCompte;
  }

  public String getNameTypeProfile() {
    return nameTypeProfile;
  }

  public void setNameTypeProfile(String nameTypeProfile) {
    this.nameTypeProfile = nameTypeProfile;
  }

  public Worker getWorker() {
    return worker;
  }

  public void setWorker(Worker worker) {
    this.worker = worker;
  }
}
