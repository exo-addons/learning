package org.exoplatform.addon.perkstore.service.utils;

import org.exoplatform.addon.perkstore.dao.CourseDao;
import org.exoplatform.addon.perkstore.dto.CourseDTO;
import org.exoplatform.addon.perkstore.entity.Course;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import java.util.List;

public class CourseService {
  private              CourseDao    courseDao;
  private static final Log          LOG = ExoLogger.getExoLogger(Course.class);

  public CourseService(CourseDao courseDao) {
    this.courseDao = courseDao;
  }

  public void setCourseDao(CourseDao courseDao) {
    this.courseDao = courseDao;
  }

  public List<Course> getAllCourse(){
    LOG.info("get All Course Service"+this.courseDao.findAll());
    return this.courseDao.findAll();
  }
    public CourseDTO getcours(Long id){
      Course course=courseDao.find(id);
      int visibility = (course.getVisibiltyCourse()) ? 1 : 0;
      CourseDTO courseDTO=new CourseDTO();
      courseDTO.setIdCourse(course.getIdCourse());
      courseDTO.setNameCourse(course.getNameCourse());
      courseDTO.setVisibilityCourse(visibility);
      courseDTO.setDateStart(course.getDateStart().toString());
      courseDTO.setDateEnd(course.getDateEnd().toString());
      courseDTO.setRewardCourse(course.getRewardCourse());
      courseDTO.setNbPerson(course.getNbPerson());
      courseDTO.setIdCategory(course.getCategory().getIdCategory());
      return courseDTO;

    }
}
