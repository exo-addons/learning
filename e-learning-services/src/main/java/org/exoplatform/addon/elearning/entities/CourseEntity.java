package org.exoplatform.addon.elearning.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.exoplatform.commons.api.persistence.ExoEntity;

import lombok.Data;

@Entity(name = "COURSE")
@ExoEntity
@Table(name = "COURSE_TABLE")
public @Data class CourseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "COURSE_ID")
  private Long      courseId;

  @Column(name = "COURSE_TITLE")
  private String    courseTitle;

  @Column(name = "COURSE_DESCRIPTION")
  private String    courseDescription;

  @Column(name = "COURSE_AUTHOR_ID")
  private Long      courseAuthor;

  @Column(name = "COURSE_CREATED_DATE")
  private Timestamp createdDate;

  @Column(name = "COURSE_MODIFIED_DATE")
  private Timestamp modifiedDate;

  @Column(name = "COURSE_LAST_CREATED_DATE")
  private Timestamp lastModifiedDate;

  @Column(name = "COURSE_ILLUSTRATION")
  private Long      courseIllustration;

  @Column(name = "COURSE_MEDIA_TYPE")
  private String    courseMediaType;

  @Column(name = "COURSE_DURATION")
  private String    courseDuration;

  public CourseEntity(String courseTitle, String courseDescription) {
    this.courseTitle = courseTitle;
    this.courseDescription = courseDescription;
  }

  public CourseEntity() {
    super();
  }

  public enum CourseMediaType {
    Video, Sildes, Text, Other
  }

  public enum CourseLevel {
    Basic, Intermediate, Advanced, Superior
  }

}
