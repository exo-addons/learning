package org.exoplatform.addon.elearning.service.mapper;

import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.addon.elearning.entities.LessonEntity;
import org.exoplatform.addon.elearning.service.dto.LessonDTO;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LessonMapper {
  protected IdentityManager identityManager = null;

  public LessonMapper() {
    identityManager = CommonsUtils.getService(IdentityManager.class);

  }
  public LessonDTO lessonToLessonDTO(LessonEntity lesson){
    return new LessonDTO(lesson);
  }
  public List<LessonDTO> lessonsToLessonDTOs(List<LessonEntity>lessons){
    return lessons.stream()
                  .filter(Objects::nonNull)
                  .map(this::lessonToLessonDTO)
                  .collect(Collectors.toList());
  }
  public LessonEntity lessonDTOToLesson(LessonDTO lessonDTO){
    try {
      if(lessonDTO==null){
        return null;
      }else{
        String user= ConversationState.getCurrent().getIdentity().getUserId();
        LessonEntity lesson=new LessonEntity();
        lesson.setTitleLesson(lessonDTO.getTitleLesson());
        lesson.setUserName(user);
        lesson.setDescriptionLesson(lessonDTO.getDescriptionLesson());
        lesson.setContentLesson(lessonDTO.getContentLesson());
        CourseEntity course=this.lessonFromLongId(lessonDTO.getIdCourse());
        lesson.setCourse(course);
        return lesson;

      }

    }catch(Exception pe){
      pe.printStackTrace();
    }
    return null;
  }
  public CourseEntity lessonFromLongId(Long idCourse) {
    CourseEntity course=new CourseEntity();
    course.setIdCourse(idCourse);
    return course;
  }

}
