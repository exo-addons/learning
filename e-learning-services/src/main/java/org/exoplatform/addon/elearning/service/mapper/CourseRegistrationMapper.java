package org.exoplatform.addon.elearning.service.mapper;

import org.exoplatform.addon.elearning.entities.CourseRegistrationEntity;
import org.exoplatform.addon.elearning.service.dto.CourseRegistrationDTO;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseRegistrationMapper {
  private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  public CourseRegistrationMapper() {
  }
  public CourseRegistrationDTO courseRegistrationToCourseRegistrationDTO(CourseRegistrationEntity course){
    return new CourseRegistrationDTO(course);
  }
  public List<CourseRegistrationDTO>coursesRegistrationsToCourseRegistartionDTOs(List<CourseRegistrationEntity> courses){
    return courses.stream()
                  .filter(Objects::nonNull)
                  .map(this::courseRegistrationToCourseRegistrationDTO)
                  .collect(Collectors.toList());
  }

}
