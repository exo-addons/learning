package org.exoplatform.addon.elearning.service.dto;

import org.exoplatform.addon.elearning.entities.LessonEntity;

import java.io.Serializable;

public class LessonDTO   implements Serializable {
  private Long idLesson;
  private String descriptionLesson;
  private String contentLesson;
  private Long idCourse;

  public LessonDTO() {
  }

  public LessonDTO(LessonEntity lesson) {
    this.idLesson = lesson.getIdLesson();
    this.descriptionLesson = lesson.getDescriptionLesson();
    this.contentLesson =lesson.getContentLesson();
    this.idCourse=lesson.getCourse().getIdCourse();
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

  public String getContentLesson() {
    return contentLesson;
  }

  public void setContentLesson(String contentLesson) {
    this.contentLesson = contentLesson;
  }

  public Long getIdCourse() {
    return idCourse;
  }

  public void setIdCourse(Long idCourse) {
    this.idCourse = idCourse;
  }
}
