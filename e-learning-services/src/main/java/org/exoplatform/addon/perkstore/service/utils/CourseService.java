package org.exoplatform.addon.perkstore.service.utils;

import org.exoplatform.addon.perkstore.dao.CourseDao;
import org.exoplatform.addon.perkstore.dto.CourseDTO;
import org.exoplatform.addon.perkstore.dto.CourseMapper;
import org.exoplatform.addon.perkstore.entity.Course;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import java.util.Date;
import java.util.List;

public class CourseService {
  private              CourseDao    courseDao;
  private              CourseMapper courseMapper;
  private static final Log          LOG = ExoLogger.getExoLogger(Course.class);

  public CourseService(CourseDao courseDao, CourseMapper courseMapper) {
    this.courseDao = courseDao;
    this.courseMapper = courseMapper;
  }

  /*public Course addCourse(Course cr){
    Course addCourse =null;
   String libelleCours=null;
   Boolean visibiliteCours=false;
   Date dateDebut=null;
   Date dateFin=null;
   int nbPersonne=0;
   String recompenseCours="";
    //if(!cr.getLibelleCours().equals("")&&(cr.getDateDebut().before(cr.getDateFin())||cr.getDateDebut().equals(cr.getDateFin()))) {
      Course course = new Course();
      course.setNameCourse(cr.getNameCourse().trim());
      course.setVisibiltyCourse(cr.getVisibiltyCourse());
      course.setDateStart(cr.getDateStart());
      course.setDateEnd(cr.getDateEnd());
      course.setNbPerson(cr.getNbPerson());
      course.setRewardCourse(cr.getRewardCourse());
      try {
        addCourse = courseDao.create(course);
        return addCourse;
      } catch (Exception e) {
        LOG.error("Cannot Create the course", e);
      }
    //}
    return addCourse;
  }
  */
  /*
 public Course addCours(Course cr){
   Course crs=new Course();
   if((!cr.getLibelleCours().equals(""))&&(!cr.getDateDebut().equals(""))&&(!cr.getDateFin().equals(""))) {
     try {
       LOG.info("Course is added with success");
       crs=courseDao.create(cr);
     }
     catch(Exception e){
       LOG.error("Cannot create the article", e);

     }
   }
   return crs;
 }*/



  /*public List<Course> getAllCourse(){
    LOG.info("get All Course Service"+this.courseDao.findAll());
    return this.courseDao.findAll();
  }*/

  public List<CourseDTO>getAllCourses(){
    try {
      List<Course> courses=courseDao.findAll();
      if(courses!=null){
        LOG.info("Contenu de list"+courseMapper.coursesToCourseDTOs(courses));
        return  courseMapper.coursesToCourseDTOs(courses);
      }

    }catch (Exception e){
      LOG.error("Error to find Courses", e.getMessage());

    }
    return null;
  }

  @ExoTransactional
  public CourseDTO addCourse(CourseDTO cr){
    Course course=null;
    try{
      course=courseDao.create(courseMapper.courseDTOToCourse(cr));
    }catch(Exception e){
      LOG.error("Error to find Courses", e.getMessage());
    }
    return courseMapper.courseToCourseDTO(course);
  }
}
