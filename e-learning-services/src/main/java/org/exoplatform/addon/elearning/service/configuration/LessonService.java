package org.exoplatform.addon.elearning.service.configuration;

import org.exoplatform.addon.elearning.entities.LessonEntity;
import org.exoplatform.addon.elearning.service.dto.LessonDTO;
import org.exoplatform.addon.elearning.service.mapper.LessonMapper;
import org.exoplatform.addon.elearning.storage.LessonDao;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import java.util.List;

public class LessonService {
  private LessonDao lessonDao;
  private LessonMapper lessonMapper;

  private static final Log LOG = ExoLogger.getExoLogger(LessonEntity.class);

  public LessonService() {
    this.lessonDao = CommonsUtils.getService(LessonDao.class);
    this.lessonMapper = CommonsUtils.getService(LessonMapper.class);
  }
  public List<LessonDTO> getAllLessons() {
    try {
      //--- load all Lessons
      List<LessonEntity> lessons = lessonDao.findAll();
      LOG.info("ok"+lessons);
      if (lessons != null) {
        return lessonMapper.lessonsToLessonDTOs(lessons);
      }

    } catch (Exception e) {
      LOG.error("Error to find Lesssons", e.getMessage());
    }
    return null;

  }
  @ExoTransactional
  public LessonDTO addLesson (LessonDTO lessonDTO) {

    LessonEntity lesson = null;

    try {

      lesson = lessonDao.create(lessonMapper.lessonDTOToLesson(lessonDTO));

    } catch (Exception e) {
      LOG.error("Error to create lesson with title {}", lessonDTO.getDescriptionLesson(), e);
    }

    return lessonMapper.lessonToLessonDTO(lesson);
  }

}
