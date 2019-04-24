package org.exoplatform.addon.elearning.service.configuration;
import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.addon.elearning.storage.CourseDao;
import org.exoplatform.addon.elearning.service.dto.CourseDTO;
import org.exoplatform.addon.elearning.service.mapper.CourseMapper;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import java.util.List;

public class CourseService {
  private              CourseDao    courseDao;
  private              CourseMapper courseMapper;
  private static final Log          LOG = ExoLogger.getExoLogger(CourseEntity.class);

  public CourseService(CourseDao courseDao, CourseMapper courseMapper) {
    this.courseDao =  CommonsUtils.getService(CourseDao.class);
    this.courseMapper=  CommonsUtils.getService(CourseMapper.class);
  }
  public List<CourseDTO> getAllCourses() {
    try {
      //--- load all Rules
      List<CourseEntity> courses = courseDao.findAll();
      if (courses != null) {
        return courseMapper.coursesToCourseDTOs(courses);
      }

    } catch (Exception e) {
      LOG.error("Error to find Courses", e.getMessage());
    }
    return null;

  }
  @ExoTransactional
  public CourseDTO addCours (CourseDTO coursDTO) {

    CourseEntity cours = null;

    try {

      cours = courseDao.create(courseMapper.courseDTOToCourse(coursDTO));

    } catch (Exception e) {
      LOG.error("Error to create badge with title {}", coursDTO.getNameCourse() , e);
    }

    return courseMapper.courseToCourseDTO(cours);
  }
}
