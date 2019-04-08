package org.exoplatform.addon.perkstore.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;

@Entity
@ExoEntity
@Table(name = "ELEARNING_EXERCISE")
public class Exercise {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_EXERCISE")
  private Long   idExercise;
  @Column(name ="QUESTION_EXERCISE")
  private String questionExercise;
  @Column(name ="ANSWER_EXERCISE")
  private String answerExercise;
  @Column(name ="SCALE_EXERCISE")
  private Long   scaleExercise;
  @ManyToOne
  @JoinColumn(name = "LECON_ID")
  private Lesson lesson;
  @ManyToOne
  @JoinColumn(name = "EXAMEN_ID")
  private Exam   exam;

  public Exercise() {
  }

  public Exercise(String questionExercise,
                  String answerExercise,
                  Long scaleExercise,
                  Lesson lesson, Exam exam) {
    this.questionExercise = questionExercise;
    this.answerExercise = answerExercise;
    this.scaleExercise = scaleExercise;
    this.lesson = lesson;
    this.exam = exam;
  }

  public Long getIdExercise() {
    return idExercise;
  }

  public void setIdExercise(Long idExercise) {
    this.idExercise = idExercise;
  }

  public String getQuestionExercise() {
    return questionExercise;
  }

  public void setQuestionExercise(String questionExercise) {
    this.questionExercise = questionExercise;
  }

  public String getAnswerExercise() {
    return answerExercise;
  }

  public void setAnswerExercise(String answerExercise) {
    this.answerExercise = answerExercise;
  }

  public Long getScaleExercise() {
    return scaleExercise;
  }

  public void setScaleExercise(Long scaleExercise) {
    this.scaleExercise = scaleExercise;
  }

  public Lesson getLesson() {
    return lesson;
  }

  public void setLesson(Lesson lesson) {
    this.lesson = lesson;
  }

  public Exam getExam() {
    return exam;
  }

  public void setExam(Exam exam) {
    this.exam = exam;
  }
}
