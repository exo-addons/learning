package org.exoplatform.addon.perkstore.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;
@Entity
@ExoEntity
@Table(name = "ELEARNING_COURS")
public class Cours {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_COURS")
  private Long idCours;
  @Column(name ="LIBELLE_COURS")
  private String libelleCours;
  @Column(name ="VISIBILITY_COURS")
  private Boolean visibilteCours;
  @Column(name ="DATE_DEB")
  private Date dateDebut;
  @Column(name ="DATE_FIN")
  private Date dateFin;
  @Column(name ="NB_PERSONNE")
  private int nbPersonne;
  @Column(name ="RECOMPENSE_COURS")
  private String recompenseCours;

  public Cours() {
  }

  public Cours(String libelleCours,
               Boolean visibilteCours,
               Date dateDebut,
               Date dateFin,
               int nbPersonne,
               String recompenseCours) {
    this.libelleCours = libelleCours;
    this.visibilteCours = visibilteCours;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
    this.nbPersonne = nbPersonne;
    this.recompenseCours = recompenseCours;
  }

  public Long getIdCours() {
    return idCours;
  }

  public void setIdCours(Long idCours) {
    this.idCours = idCours;
  }

  public String getLibelleCours() {
    return libelleCours;
  }

  public void setLibelleCours(String libelleCours) {
    this.libelleCours = libelleCours;
  }

  public Boolean getVisibilteCours() {
    return visibilteCours;
  }

  public void setVisibilteCours(Boolean visibilteCours) {
    this.visibilteCours = visibilteCours;
  }

  public Date getDateDebut() {
    return dateDebut;
  }

  public void setDateDebut(Date dateDebut) {
    this.dateDebut = dateDebut;
  }

  public Date getDateFin() {
    return dateFin;
  }

  public void setDateFin(Date dateFin) {
    this.dateFin = dateFin;
  }

  public int getNbPersonne() {
    return nbPersonne;
  }

  public void setNbPersonne(int nbPersonne) {
    this.nbPersonne = nbPersonne;
  }

  public String getRecompenseCours() {
    return recompenseCours;
  }

  public void setRecompenseCours(String recompenseCours) {
    this.recompenseCours = recompenseCours;
  }

  @Override
  public String toString() {
    return "Cours{" +
        "idCours=" + idCours +
        ", libelleCours='" + libelleCours + '\'' +
        ", visibilteCours=" + visibilteCours +
        ", dateDebut=" + dateDebut +
        ", dateFin=" + dateFin +
        ", nbPersonne=" + nbPersonne +
        ", recompenseCours='" + recompenseCours + '\'' +
        '}';
  }
}
