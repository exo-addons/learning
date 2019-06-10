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
  public LessonDTO getLesson(Long id) {
    try {
      LessonEntity lesson = lessonDao.find(id);
      if (lesson != null) {
        return lessonMapper.lessonToLessonDTO(lesson);
      }
    } catch (Exception e) {
      LOG.error("Error to find Lesson by id", e.getMessage());
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

  @ExoTransactional
  public List<LessonDTO> findLessonsByCourseId(Long id){
    try {
      List<LessonEntity> lessons=lessonDao.findLessonsByIdCourse(id);
      if(lessons!=null){
        return lessonMapper.lessonsToLessonDTOs(lessons);
      }
    }catch(Exception e){
      LOG.error("Error to find Lesson entity with id : {}", id, e.getMessage());
    }
    return null;

  }
  @ExoTransactional
  public void deleteLesson(Long id) {
    LessonEntity lesson=lessonDao.find(id);
    if(lesson!=null) {
      try {

        lessonDao.delete(lesson);

      } catch (Exception e) {
        LOG.error("Error to delete Lesson with id {}", id, e);
      }
    }
  }


  @ExoTransactional
  public LessonDTO updateLesson(LessonDTO lessonDTO){
    try{
      LessonEntity lessonEntity=lessonDao.find(lessonDTO.getIdLesson());
      if(lessonEntity!=null){
        lessonEntity.setTitleLesson(lessonDTO.getTitleLesson());
        lessonEntity.setContentLesson(lessonDTO.getContentLesson());
        lessonEntity.setDescriptionLesson(lessonDTO.getDescriptionLesson());
        return lessonMapper.lessonToLessonDTO(lessonEntity);
      }

    }catch (Exception e){
      LOG.error("Error to update with id {}", lessonDTO.getTitleLesson() , e);
    }
    return null;
  }

}
