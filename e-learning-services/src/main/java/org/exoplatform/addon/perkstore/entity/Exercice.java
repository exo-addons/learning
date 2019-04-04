package org.exoplatform.addon.perkstore.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;

@Entity
@ExoEntity
@Table(name = "ELEARNING_EXERCICE")
public class Exercice {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_EXERCICE")
  private Long idExercice;
  @Column(name ="QUESTION_EXERCICE")
  private String questionExercice;
  @Column(name ="REPONSE_EXERCICE")
  private String reponseExercice;
  @Column(name ="BAREME_EXERCICE")
  private Long baremExercice;
  @ManyToOne
  @JoinColumn(name = "LECON_ID")
  private Lecon lecon;
  @ManyToOne
  @JoinColumn(name = "EXAMEN_ID")
  private Examen examen;

  public Exercice() {
  }

  public Exercice(String questionExercice,
                  String reponseExercice,
                  Long baremExercice,
                  Lecon lecon, Examen examen) {
    this.questionExercice = questionExercice;
    this.reponseExercice = reponseExercice;
    this.baremExercice = baremExercice;
    this.lecon = lecon;
    this.examen = examen;
  }

  public Long getIdExercice() {
    return idExercice;
  }

  public void setIdExercice(Long idExercice) {
    this.idExercice = idExercice;
  }

  public String getQuestionExercice() {
    return questionExercice;
  }

  public void setQuestionExercice(String questionExercice) {
    this.questionExercice = questionExercice;
  }

  public String getReponseExercice() {
    return reponseExercice;
  }

  public void setReponseExercice(String reponseExercice) {
    this.reponseExercice = reponseExercice;
  }

  public Long getBaremExercice() {
    return baremExercice;
  }

  public void setBaremExercice(Long baremExercice) {
    this.baremExercice = baremExercice;
  }

  public Lecon getLecon() {
    return lecon;
  }

  public void setLecon(Lecon lecon) {
    this.lecon = lecon;
  }

  public Examen getExamen() {
    return examen;
  }

  public void setExamen(Examen examen) {
    this.examen = examen;
  }
}
