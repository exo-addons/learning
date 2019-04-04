package org.exoplatform.addon.perkstore.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
  @OneToMany(cascade=CascadeType.ALL,mappedBy ="cours")
  private List<Lecon> lecons;
  @ManyToOne
  @JoinColumn(name = "CATEGORY_ID")
  private Category category;

  public Cours() {
  }

  public Cours(String libelleCours,
               Boolean visibilteCours,
               Date dateDebut,
               Date dateFin,
               int nbPersonne,
               String recompenseCour) {
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

  public List<Lecon> getLecons() {
    return lecons;
  }

  public void setLecons(List<Lecon> lecons) {
    this.lecons = lecons;
  }
}
