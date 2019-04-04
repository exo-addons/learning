package org.exoplatform.addon.perkstore.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@ExoEntity
@Table(name = "ELEARNING_EXAMEN")
public class Examen {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_EXAMEN")
  private Long           idExamen;
  @Column(name ="LIBELLE_EXAMEN")
  private String         libelleExamen;
  @Column(name ="DATE_DEB_EXAMEN")
  private Date           dateDebExamen;
  @Column(name ="DATE_FIN_EXAMEN")
  private Date           dateFinExamen;
  @Column(name ="NBRE_TENTATIVE_EXAMEN")
  private Long           nbreTentativeExamen;
  @Column(name = "RECOMPENSE_EXAMEN")
  private String         recompenseExamen;
  @OneToMany(mappedBy = "examen")
  private List<Exercice> exercices;

  public Examen() {
  }

  public Examen(String libelleExamen,
                Date dateDebExamen,
                Date dateFinExamen,
                Long nbreTentativeExamen,
                String recompenseExamen) {
    this.libelleExamen = libelleExamen;
    this.dateDebExamen = dateDebExamen;
    this.dateFinExamen = dateFinExamen;
    this.nbreTentativeExamen = nbreTentativeExamen;
    this.recompenseExamen = recompenseExamen;
  }

  public Long getIdExamen() {
    return idExamen;
  }

  public void setIdExamen(Long idExamen) {
    this.idExamen = idExamen;
  }

  public String getLibelleExamen() {
    return libelleExamen;
  }

  public void setLibelleExamen(String libelleExamen) {
    this.libelleExamen = libelleExamen;
  }

  public Date getDateDebExamen() {
    return dateDebExamen;
  }

  public void setDateDebExamen(Date dateDebExamen) {
    this.dateDebExamen = dateDebExamen;
  }

  public Date getDateFinExamen() {
    return dateFinExamen;
  }

  public void setDateFinExamen(Date dateFinExamen) {
    this.dateFinExamen = dateFinExamen;
  }

  public Long getNbreTentativeExamen() {
    return nbreTentativeExamen;
  }

  public void setNbreTentativeExamen(Long nbreTentativeExamen) {
    this.nbreTentativeExamen = nbreTentativeExamen;
  }

  public String getRecompenseExamen() {
    return recompenseExamen;
  }

  public void setRecompenseExamen(String recompenseExamen) {
    this.recompenseExamen = recompenseExamen;
  }
}
