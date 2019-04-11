package org.exoplatform.addon.elearning.service.utils;

import org.exoplatform.addon.elearning.dao.CategoryDao;
import org.exoplatform.addon.elearning.dao.CourseDao;
import org.exoplatform.addon.elearning.dto.CourseDTO;
import org.exoplatform.addon.elearning.entity.Category;
import org.exoplatform.addon.elearning.entity.Course;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CourseService {
  private              CourseDao    courseDao;
  private CategoryDao categoryDao;
  private static final Log          LOG = ExoLogger.getExoLogger(Course.class);

  public CourseService(CourseDao courseDao, CategoryDao categoryDao) {
    this.courseDao = courseDao;
    this.categoryDao = categoryDao;
  }

  public void setCategoryDao(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
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
      CourseDTO courseDTO=new CourseDTO();
      courseDTO.setIdCourse(course.getIdCourse());
      courseDTO.setNameCourse(course.getNameCourse());
      courseDTO.setVisibilityCourse(course.getVisibiltyCourse());
      courseDTO.setDateStart(course.getDateStart().toString());
      courseDTO.setDateEnd(course.getDateEnd().toString());
      courseDTO.setRewardCourse(course.getRewardCourse());
      courseDTO.setNbPerson(course.getNbPerson());
      courseDTO.setIdCategory(course.getCategory().getIdCategory());
      return courseDTO;

    }
    public Course addCourse(CourseDTO courseDTO){
      Category category=categoryDao.find(courseDTO.getIdCategory());
      Date dateStart= null;
      try {
        dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(courseDTO.getDateStart());
      } catch (ParseException e) {
        e.printStackTrace();
      }
      Date dateFin= null;
      try {
        dateFin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(courseDTO.getDateEnd());
      } catch (ParseException e) {
        e.printStackTrace();
      }
      Course course=new Course(courseDTO.getNameCourse(),courseDTO.getVisibilityCourse(),dateStart,dateFin,courseDTO.getNbPerson(),courseDTO.getRewardCourse(),category);
      System.out.println("***********************************"+course.getCategory().getIdCategory());
      courseDao.create(course);
      return course;
    }
}
