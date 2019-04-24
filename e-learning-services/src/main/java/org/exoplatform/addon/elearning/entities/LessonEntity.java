package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@ExoEntity
@Table(name = "ELEARNING_LESSON")
public class LessonEntity {
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
  private CourseEntity course;

  public LessonEntity() {
  }

  public LessonEntity(String descriptionLesson,
                      String titreLesson,
                      String contentLesson,
                      CourseEntity course, List<ExerciseEntity> exercises) {
    this.descriptionLesson = descriptionLesson;
    this.titleLesson = titreLesson;
    this.contentLesson = contentLesson;
    this.course = course;
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

  public CourseEntity getCourse() {
    return course;
  }

  public void setCourse(CourseEntity course) {
    this.course = course;
  }

}
