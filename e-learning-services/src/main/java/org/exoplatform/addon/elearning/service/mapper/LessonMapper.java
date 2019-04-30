package org.exoplatform.addon.elearning.service.mapper;

import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.addon.elearning.entities.LessonEntity;
import org.exoplatform.addon.elearning.service.dto.LessonDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LessonMapper {
  public LessonMapper() {
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
        LessonEntity lesson=new LessonEntity();
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
