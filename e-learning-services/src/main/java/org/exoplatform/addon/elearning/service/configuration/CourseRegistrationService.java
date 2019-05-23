package org.exoplatform.addon.elearning.service.configuration;

import org.exoplatform.addon.elearning.entities.CourseRegistrationEntity;
import org.exoplatform.addon.elearning.service.dto.CourseRegistrationDTO;
import org.exoplatform.addon.elearning.service.mapper.CourseRegistrationMapper;
import org.exoplatform.addon.elearning.storage.CourseRegistrationDao;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import java.util.List;

public class CourseRegistrationService {
  private              CourseRegistrationDao    courseDao;
  private              CourseRegistrationMapper courseMapper;
  private static final Log                      LOG = ExoLogger.getExoLogger(CourseRegistrationEntity.class);

  public CourseRegistrationService() {
    this.courseDao = CommonsUtils.getService(CourseRegistrationDao.class);
    this.courseMapper = CommonsUtils.getService(CourseRegistrationMapper.class);
  }
  @ExoTransactional
  public CourseRegistrationDTO addCours (CourseRegistrationDTO coursRegistrationDTO) {
    List<CourseRegistrationEntity> courseRegistrationDaos=courseDao.findAll();

    CourseRegistrationEntity cours = null;

    try {

      cours = courseDao.create(courseMapper.courseRegistrationDTOToCourseRegistration(coursRegistrationDTO));


    } catch (Exception e) {
      LOG.error("Error to create course with title {}", coursRegistrationDTO.getIdCourse() , e);
    }

    return courseMapper.courseRegistrationToCourseRegistrationDTO(cours);
  }
  public List<CourseRegistrationDTO> getAllCoursesRegitered(String worker) {
    try {
      //--- load all registrations
      List<CourseRegistrationEntity> courses = courseDao.findAll();
      if (courses != null) {
        return courseMapper.coursesRegistrationsToCourseRegistartionDTOs(courses);
      }

    } catch (Exception e) {
      LOG.error("Error to find Courses", e.getMessage());
    }
    return null;

  }

  public Long getNumberWorker(Long id){
    return courseDao.getCountNumberEmploye(id);
  }

}
