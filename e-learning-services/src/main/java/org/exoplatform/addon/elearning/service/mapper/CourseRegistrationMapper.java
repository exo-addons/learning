package org.exoplatform.addon.elearning.service.mapper;

import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.addon.elearning.entities.CourseRegistrationEntity;
import org.exoplatform.addon.elearning.entities.WorkerEntity;
import org.exoplatform.addon.elearning.service.dto.CourseRegistrationDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
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
  public CourseRegistrationEntity courseRegistrationDTOToCourseRegistration(CourseRegistrationDTO courseRegistrationDTO){
    try {
      if(courseRegistrationDTO==null){
        return null;
      }else{
        CourseRegistrationEntity courseRegistration=new CourseRegistrationEntity();
        courseRegistration.setRegisteredAt(new Date());
        WorkerEntity worker=this.workerFromLongId(courseRegistrationDTO.getIdWorker());
        courseRegistration.setWorker(worker);
        CourseEntity course=this.courseFromLongId(courseRegistrationDTO.getIdCourse());
        courseRegistration.setCourse(course);
        return courseRegistration;

      }

    }catch(Exception pe){
      pe.printStackTrace();
    }
    return null;
  }
  public WorkerEntity workerFromLongId(Long idWorker) {
    WorkerEntity worker=new WorkerEntity();
    worker.setIdWorker(idWorker);
    return worker;
  }
  public CourseEntity courseFromLongId(Long idCourse) {
    CourseEntity course=new CourseEntity();
    course.setIdCourse(idCourse);
    return course;
  }


}
