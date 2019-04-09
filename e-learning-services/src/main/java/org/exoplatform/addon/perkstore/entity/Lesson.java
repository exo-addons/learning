package org.exoplatform.addon.perkstore.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@ExoEntity
@Table(name = "ELEARNING_LESSON")
public class Lesson {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_LESSON")
  private Long idLesson;

  @Column(name = "DESCRIPTION_LESSON")
  private String descriptionLesson;

  @Column(name = "TITLE_LESSON")
  private String titleLesson;

  @Column(name = "CONTENT_LESSON")
  private String contentLesson;

  @ManyToOne
  @JoinColumn(name = "COURSE_ID")
  private Course course;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
  private List<Exercise> exercises;

  public Lesson() {
  }

  public Lesson(String descriptionLesson,
                String titreLesson,
                String contentLesson,
                Course course, List<Exercise> exercises) {
    this.descriptionLesson = descriptionLesson;
    this.titleLesson = titreLesson;
    this.contentLesson = contentLesson;
    this.course = course;
    this.exercises = exercises;
  }

  public Long getIdLesson() {
    return idLesson;
  }

  public void setIdLesson(Long idLesson) {
    this.idLesson = idLesson;
  }

  public String getDescriptionLesson() {
    return descriptionLesson;
  }

  public void setDescriptionLesson(String descriptionLesson) {
    this.descriptionLesson = descriptionLesson;
  }

  public String getTitreLesson() {
    return titleLesson;
  }

  public void setTitreLesson(String titreLesson) {
    this.titleLesson = titreLesson;
  }

  public String getContentLesson() {
    return contentLesson;
  }

  public void setContentLesson(String contentLesson) {
    this.contentLesson = contentLesson;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public List<Exercise> getExercises() {
    return exercises;
  }

  public void setExercises(List<Exercise> exercises) {
    this.exercises = exercises;
  }
}
