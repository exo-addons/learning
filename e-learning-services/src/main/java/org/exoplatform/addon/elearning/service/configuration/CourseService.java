package org.exoplatform.addon.elearning.service.configuration;

import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.addon.elearning.storage.CourseDao;
import org.exoplatform.addon.elearning.service.dto.CourseDTO;
import org.exoplatform.addon.elearning.service.mapper.CourseMapper;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationState;

import java.util.List;

public class CourseService {
    private CourseDao courseDao;
    private CourseMapper courseMapper;
    private static final Log LOG = ExoLogger.getExoLogger(CourseEntity.class);

    public CourseService(CourseDao courseDao, CourseMapper courseMapper) {
        this.courseDao = CommonsUtils.getService(CourseDao.class);
        this.courseMapper = CommonsUtils.getService(CourseMapper.class);
    }

    public List<CourseDTO> getAllCourses() {
        try {
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
    public CourseDTO addCours(CourseDTO coursDTO) {

        CourseEntity cours = null;

        try {

            cours = courseDao.create(courseMapper.courseDTOToCourse(coursDTO));


        } catch (Exception e) {
            LOG.error("Error to create course with title {}", coursDTO.getNameCourse(), e);
        }

        return courseMapper.courseToCourseDTO(cours);
    }

    @ExoTransactional
    public CourseDTO findCourseByName(String courseName) {

        try {
            //--- Get Entity from DB
            CourseEntity entity = courseDao.findCourseByName(courseName);
            //--- Convert Entity to DTO
            if (entity != null) {
                return courseMapper.courseToCourseDTO(entity);
            }

        } catch (Exception e) {
            LOG.error("Error to find Course entity with title : {}", courseName, e.getMessage());
        }
        return null;

    }

    public List<CourseDTO> getCourseByUserAndStatus(String status, String user) {
        try {
            List<CourseEntity> course = courseDao.getCourseByUserAndStatus(status, user);
            if (course != null) {
                return courseMapper.coursesToCourseDTOs(course);
            }

        } catch (Exception e) {
            LOG.error("Error to find archived course", e.getMessage());
        }
        return null;

    }

    public List<CourseDTO> getOtherPublishedCourse(String user) {
        List<CourseEntity> course = courseDao.getOtherPublishedCourse(user);
        if (course != null) {
            return courseMapper.coursesToCourseDTOs(course);
        }
        return null;
    }

    @ExoTransactional
    public void deleteCourseById(Long id) {
        CourseEntity course = courseDao.find(id);
        courseDao.delete(course);
    }

    @ExoTransactional
    public CourseDTO updateCourse(CourseDTO courseDTO) {
        CourseEntity courseEntity = courseDao.find(courseDTO.getIdCourse());
        if (courseEntity != null) {
            courseEntity.setNameCourse(courseDTO.getNameCourse());
            courseEntity.setNbPerson(courseDTO.getNbPerson());
            courseEntity.setRewardCourse(courseDTO.getRewardCourse());
            courseEntity.setStatus(courseDTO.getStatus());
            return courseMapper.courseToCourseDTO(courseEntity);
        }
        return null;
    }
}
