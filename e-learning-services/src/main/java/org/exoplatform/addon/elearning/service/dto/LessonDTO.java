package org.exoplatform.addon.elearning.service.dto;

import org.exoplatform.addon.elearning.entities.LessonEntity;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import java.io.Serializable;

public class LessonDTO   implements Serializable {
 // protected IdentityManager identityManager = null;

  private Long idLesson;
  private String titleLesson;
  private String descriptionLesson;
  private String contentLesson;
  private Long idCourse;
  private String userName;


  public LessonDTO() {
    //identityManager = CommonsUtils.getService(IdentityManager.class);
  }

  public LessonDTO(LessonEntity lesson) {
    //String user= ConversationState.getCurrent().getIdentity().getUserId();

    this.idLesson = lesson.getIdLesson();
    this.userName=lesson.getUserName();
    this.titleLesson=lesson.getTitleLesson();
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

  public String getTitleLesson() {
    return titleLesson;
  }

  public void setTitleLesson(String titleLesson) {
    this.titleLesson = titleLesson;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
