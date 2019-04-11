/*
package org.exoplatform.addon.elearning.dto;
import org.exoplatform.addon.elearning.entity.Category;
import org.exoplatform.addon.elearning.entity.Course;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper
public class CourseMapper {
  public CourseMapper() {
  }
  public CourseDTO courseToCourseDTO(Course course){
    return new CourseDTO(course);
  }
  public List<CourseDTO>coursesToCourseDTOs(List<Course>courses){
    return courses.stream()
                 .filter(Objects::nonNull)
                 .map(this::courseToCourseDTO)
                 .collect(Collectors.toList());
  }
  public Course courseDTOToCourse(CourseDTO courseDTO){
    CategoryDTO categoryDTO=null;//new Category(1L,"llll");
    try {
      if(courseDTO==null){
        return null;
      }else{
        Course course=new Course();
        course.setNameCourse(courseDTO.getNameCourse());
        course.setVisibiltyCourse(courseDTO.getVisibilityCourse());
        course.setRewardCourse(courseDTO.getRewardCourse());
        course.setNbPerson(courseDTO.getNbPerson());
        course.setDateStart(courseDTO.getDateStart());
        course.setDateEnd(courseDTO.getDateEnd());
        System.out.println("id categorie"+categoryDTO.getIdCategory());
        categoryDTO.setIdCategory(courseDTO.getIdCategory());
        course.setCategory(cat);
        return course;

      }

    }catch(Exception pe){
      pe.printStackTrace();
    }
    return null;
  }
  public List<Course> courseDTOsTOCourses(List<CourseDTO> CourseDTOs){
    return CourseDTOs.stream()
                     .filter(Objects::nonNull)
                     .map(this::courseDTOToCourse)
                     .collect(Collectors.toList());
  }
}
*/
