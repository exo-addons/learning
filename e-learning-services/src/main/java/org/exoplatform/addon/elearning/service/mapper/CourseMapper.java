package org.exoplatform.addon.elearning.service.mapper;
import org.exoplatform.addon.elearning.entities.CategoryEntity;
import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.addon.elearning.service.dto.CategoryDTO;
import org.exoplatform.addon.elearning.service.dto.CourseDTO;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseMapper {
  protected      IdentityManager  identityManager = null;
  private static SimpleDateFormat formatter       = new SimpleDateFormat("yyyy-MM-dd");

  public CourseMapper() {
  }
  public CourseDTO courseToCourseDTO(CourseEntity course){
    return new CourseDTO(course);
  }
  public List<CourseDTO>coursesToCourseDTOs(List<CourseEntity>courses){
    return courses.stream()
                 .filter(Objects::nonNull)
                 .map(this::courseToCourseDTO)
                 .collect(Collectors.toList());
  }
  public CourseEntity courseDTOToCourse(CourseDTO courseDTO){
    try {
      if(courseDTO==null){
        return null;
      }else{
        String user = ConversationState.getCurrent().getIdentity().getUserId();

        CourseEntity course=new CourseEntity();
        course.setNameCourse(courseDTO.getNameCourse());
        course.setRewardCourse(courseDTO.getRewardCourse());
        course.setNbPerson(courseDTO.getNbPerson());
        if (courseDTO.getDateStart() != null) {
          course.setDateStart(formatter.parse(courseDTO.getDateStart()));
        }
        if (courseDTO.getDateEnd() != null) {
          course.setDateEnd(formatter.parse(courseDTO.getDateEnd()));
        }
        CategoryEntity category=this.coursFromLongId(courseDTO.getIdCategory());
        course.setCategory(category);
        course.setStatus(courseDTO.getStatus());
        course.setUserName(user);
        return course;

      }

    }catch(Exception pe){
      pe.printStackTrace();
    }
    return null;
  }
  public CategoryEntity coursFromLongId(Long idCategory) {
    CategoryEntity category=new CategoryEntity();
    category.setIdCategory(idCategory);
    return category;
  }

  public List<CourseEntity> courseDTOsTOCourses(List<CourseDTO> CourseDTOs){
    return CourseDTOs.stream()
                     .filter(Objects::nonNull)
                     .map(this::courseDTOToCourse)
                     .collect(Collectors.toList());
  }
}
