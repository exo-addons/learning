package org.exoplatform.addon.elearning.service.configuration;

import org.exoplatform.addon.elearning.entities.CourseRegistrationEntity;
import org.exoplatform.addon.elearning.service.dto.CourseRegistrationDTO;
import org.exoplatform.addon.elearning.service.mapper.CourseRegistrationMapper;
import org.exoplatform.addon.elearning.storage.CourseRegistrationDao;
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
  public List<CourseRegistrationDTO> getAllCoursesRegitered(String worker) {
    try {
      //--- load all Rules
      List<CourseRegistrationEntity> courses = courseDao.findRegitrationByIdWorker(worker);
      if (courses != null) {
        return courseMapper.coursesRegistrationsToCourseRegistartionDTOs(courses);
      }

    } catch (Exception e) {
      LOG.error("Error to find Courses", e.getMessage());
    }
    return null;

  }

}
