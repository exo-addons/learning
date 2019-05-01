package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Collection;
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
  private CourseEntity                         course;
  @Column(name ="USERNAME_LESSON")
  private String                               userName;
  @OneToMany(mappedBy="lesson",fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  private Collection<ExerciseEntity> exercises;

  public LessonEntity() {
  }

  public LessonEntity(String descriptionLesson,
                      String titleLesson,
                      String contentLesson,
                      CourseEntity course, String userName) {
    this.descriptionLesson = descriptionLesson;
    this.titleLesson = titleLesson;
    this.contentLesson = contentLesson;
    this.course = course;
    this.userName = userName;
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

  public String getTitleLesson() {
    return titleLesson;
  }

  public void setTitleLesson(String titleLesson) {
    this.titleLesson = titleLesson;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
