package org.exoplatform.addon.elearning.service.dto;

import org.exoplatform.addon.elearning.entities.ExerciseEntity;

import java.io.Serializable;

public class ExerciseDTO implements Serializable {
  private Long idExercise;
  private String questionExercise;
  private String choose1;
  private String choose2;
  private String choose3;
  private String answerExercise;
  private Long scaleExercise;
  private Long idCourse;
  private Long idExam;
  private String userName;

  public ExerciseDTO() {
  }

  public ExerciseDTO(ExerciseEntity exercise) {
    this.idExercise = exercise.getIdExercise();
    this.questionExercise = exercise.getQuestionExercise();
    this.choose1 = exercise.getChoose1();
    this.choose2 = exercise.getChoose2();
    this.choose3 = exercise.getChoose3();
    this.answerExercise = exercise.getAnswerExercise();
    this.scaleExercise = exercise.getScaleExercise();
    this.idCourse = exercise.getCourse().getIdCourse();
    this.idExam = exercise.getExam().getIdExam();
    this.userName = exercise.getUserName();
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

  public String getChoose1() {
    return choose1;
  }

  public void setChoose1(String choose1) {
    this.choose1 = choose1;
  }

  public String getChoose2() {
    return choose2;
  }

  public void setChoose2(String choose2) {
    this.choose2 = choose2;
  }

  public String getChoose3() {
    return choose3;
  }

  public void setChoose3(String choose3) {
    this.choose3 = choose3;
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

  public Long getIdCourse() {
    return idCourse;
  }

  public void setIdCourse(Long idCourse) {
    this.idCourse = idCourse;
  }

  public Long getIdExam() {
    return idExam;
  }

  public void setIdExam(Long idExam) {
    this.idExam = idExam;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
