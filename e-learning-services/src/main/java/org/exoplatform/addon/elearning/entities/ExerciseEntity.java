package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;

@Entity(name = "ElearningExercise")
@ExoEntity
@Table(name = "ELEARNING_EXERCISE")
@NamedQueries({
    // it is a function to get exercises by courseName for the current user
    @NamedQuery(name = "ElearningExercise.findExercisesByCourseId", query = "select e from ElearningExercise e where e.course.idCourse=:id and e.userName=:user"),
    //it is a function to get Exercises of courses( I use it for published) of the other users By id Course
    @NamedQuery(name = "ElearningExercise.findExercisesByIdForOther", query = "select e from ElearningExercise e where e.course.idCourse = :id and e.userName<>:user"),
    @NamedQuery(name = "ElearningExercise.findExercisesByIdExam", query = "select DISTINCT e from ElearningExercise e where e.exam.idExam = :id"),
    @NamedQuery(name = "ElearningExercise.findExercisesByCourseExamId", query = "select e from ElearningExercise e where e.course.idCourse=:idc and e.exam.idExam=:ide"),

})

public class ExerciseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_EXERCISE")
  private Long         idExercise;
  @Column(name ="QUESTION_EXERCISE")
  private String       questionExercise;
  @Column(name ="CHOOSE1_EXERCISE")
  private String       choose1;
  @Column(name ="CHOOSE2_EXERCISE")
  private String       choose2;
  @Column(name ="CHOOSE3_EXERCISE")
  private String       choose3;
  @Column(name ="ANSWER_EXERCISE")
  private String       answerExercise;
  @Column(name ="SCALE_EXERCISE")
  private Long         scaleExercise;
  @ManyToOne
  @JoinColumn(name = "COURSE_ID")
  private CourseEntity course;
  @ManyToOne
  @JoinColumn(name = "EXAM_ID")
  private ExamEntity   exam;
  @Column(name ="USERNAME_EXERCISE")
  private String userName;



  public ExerciseEntity() {
  }

  public ExerciseEntity(String questionExercise,
                        String choose1,
                        String choose2,
                        String choose3,
                        String answerExercise,
                        Long scaleExercise, CourseEntity course, ExamEntity exam, String userName) {
    this.questionExercise = questionExercise;
    this.choose1 = choose1;
    this.choose2 = choose2;
    this.choose3 = choose3;
    this.answerExercise = answerExercise;
    this.scaleExercise = scaleExercise;
    this.course = course;
    this.exam = exam;
    this.userName = userName;
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

  public CourseEntity getCourse() {
    return course;
  }

  public void setCourse(CourseEntity course) {
    this.course = course;
  }

  public ExamEntity getExam() {
    return exam;
  }

  public void setExam(ExamEntity exam) {
    this.exam = exam;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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
  @Override
  public boolean equals(Object obj) {
    ExerciseEntity question = (ExerciseEntity) obj;
    return this.questionExercise.equals(question.getQuestionExercise());
  }
}
