package org.exoplatform.addon.elearning.service.configuration;

import org.exoplatform.addon.elearning.entities.ExerciseEntity;
import org.exoplatform.addon.elearning.service.dto.ExerciseDTO;
import org.exoplatform.addon.elearning.service.mapper.ExerciseMapper;
import org.exoplatform.addon.elearning.storage.ExerciseDao;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationState;

import java.util.List;

public class ExerciseService {
  private              ExerciseDao    exerciseDao;
  private              ExerciseMapper exerciseMapper;
  private static final Log            LOG = ExoLogger.getExoLogger(ExerciseEntity.class);

  public ExerciseService() {
    this.exerciseDao = CommonsUtils.getService(ExerciseDao.class);
    this.exerciseMapper = CommonsUtils.getService(ExerciseMapper.class);
  }
  public List<ExerciseDTO> getAllExercises() {
    try {
      List<ExerciseEntity> exercises = exerciseDao.findAll();
      if (exercises != null) {
        return exerciseMapper.exercisesToExerciseDTOs(exercises);
      }

    } catch (Exception e) {
      LOG.error("Error to find Exercises", e.getMessage());
    }
    return null;

  }
  @ExoTransactional
  public ExerciseDTO addExercise (ExerciseDTO exerciseDTO) {

    ExerciseEntity exercise = null;

    try {

      exercise= exerciseDao.create(exerciseMapper.exerciseDTOToExercise(exerciseDTO));

    } catch (Exception e) {
      LOG.error("Error to create exercise with title {}", exerciseDTO.getQuestionExercise() , e);
    }

    return exerciseMapper.exerciseToExerciseDTO(exercise);
  }

  @ExoTransactional
  public List<ExerciseDTO> findExercisesByCourseId(Long id,String user) {

    try {
      //--- Get Entity from DB
      List<ExerciseEntity> exercises=exerciseDao.findExercisesByCourseId(id,user);
      //--- Convert Entity to DTO
      if (exercises != null) {
        return exerciseMapper.exercisesToExerciseDTOs(exercises);
      }

    } catch (Exception e) {
      LOG.error("Error to find Exercise entity with id : {}", id, e.getMessage());
    }
    return null;

  }

  @ExoTransactional
  public List<ExerciseDTO> findExercisesForOtherPublished(Long id,String user) {

    try {
      //--- Get Entity from DB
      List<ExerciseEntity> exercises=exerciseDao.findExercisesByIdForOther(id,user);
      //--- Convert Entity to DTO
      if (exercises != null) {
        return exerciseMapper.exercisesToExerciseDTOs(exercises);
      }

    } catch (Exception e) {
      LOG.error("Error to find Exercise for other users entity with id : {}", id, e.getMessage());
    }
    return null;

  }

  @ExoTransactional
  public void deleteExercise(Long id) {
    ExerciseEntity exercise=exerciseDao.find(id);
    if(exercise!=null) {
      try {

        exerciseDao.delete(exercise);

      } catch (Exception e) {
        LOG.error("Error to delete Exercise with id {}", id, e);
      }
    }
  }

  @ExoTransactional
  public  ExerciseDTO  updateExercise ( ExerciseDTO  exerciseDTO) {
    try {
      String user = ConversationState.getCurrent().getIdentity().getUserId();

      ExerciseEntity exerciseEntity = exerciseDao.find(exerciseDTO.getIdExercise());
      if(exerciseEntity!=null){
        exerciseEntity.setQuestionExercise(exerciseDTO.getQuestionExercise());
        exerciseEntity.setScaleExercise(exerciseDTO.getScaleExercise());
        exerciseEntity.setUserName(user);
        exerciseEntity.setChoose1(exerciseDTO.getChoose1());
        exerciseEntity.setChoose2(exerciseDTO.getChoose2());
        exerciseEntity.setChoose3(exerciseDTO.getChoose3());
        exerciseEntity.setAnswerExercise(exerciseDTO.getAnswerExercise());

        return exerciseMapper.exerciseToExerciseDTO(exerciseEntity);

      }
    } catch (Exception e) {
      LOG.error("Error to update with id {}", exerciseDTO.getQuestionExercise() , e);
    }

    return null;
  }

}
