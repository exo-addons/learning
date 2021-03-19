package org.exoplatform.addon.elearning.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.exoplatform.commons.api.persistence.ExoEntity;

@Entity
@ExoEntity
@Table(name = "COURSE")
public class CourseEntity {

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

  public CourseEntity(Long courseId,
                      String courseTitle,
                      String courseDescription,
                      Long courseAuthor,
                      Timestamp createdDate,
                      Timestamp modifiedDate,
                      Timestamp lastModifiedDate,
                      Long courseIllustration,
                      String courseMediaType,
                      String courseDuration) {
    super();
    this.courseId = courseId;
    this.courseTitle = courseTitle;
    this.courseDescription = courseDescription;
    this.courseAuthor = courseAuthor;
    this.createdDate = createdDate;
    this.modifiedDate = modifiedDate;
    this.lastModifiedDate = lastModifiedDate;
    this.courseIllustration = courseIllustration;
    this.courseMediaType = courseMediaType;
    this.courseDuration = courseDuration;
  }

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }

  public String getCourseTitle() {
    return courseTitle;
  }

  public void setCourseTitle(String courseTitle) {
    this.courseTitle = courseTitle;
  }

  public String getCourseDescription() {
    return courseDescription;
  }

  public void setCourseDescription(String courseDescription) {
    this.courseDescription = courseDescription;
  }

  public Long getCourseAuthor() {
    return courseAuthor;
  }

  public void setCourseAuthor(Long courseAuthor) {
    this.courseAuthor = courseAuthor;
  }

  public Timestamp getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Timestamp createdDate) {
    this.createdDate = createdDate;
  }

  public Timestamp getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(Timestamp modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public Timestamp getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Timestamp lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public Long getCourseIllustration() {
    return courseIllustration;
  }

  public void setCourseIllustration(Long courseIllustration) {
    this.courseIllustration = courseIllustration;
  }

  public String getCourseMediaType() {
    return courseMediaType;
  }

  public void setCourseMediaType(String courseMediaType) {
    this.courseMediaType = courseMediaType;
  }

  public String getCourseDuration() {
    return courseDuration;
  }

  public void setCourseDuration(String courseDuration) {
    this.courseDuration = courseDuration;
  }

  public enum CourseMediaType {
    Video, Sildes, Text, Other
  }

  public enum CourseLevel {
    Basic, Intermediate, Advanced, Superior
  }

}
