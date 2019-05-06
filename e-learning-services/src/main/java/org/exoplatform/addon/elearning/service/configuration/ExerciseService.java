package org.exoplatform.addon.elearning.service.configuration;

import org.exoplatform.addon.elearning.entities.ExerciseEntity;
import org.exoplatform.addon.elearning.service.dto.ExerciseDTO;
import org.exoplatform.addon.elearning.service.mapper.ExerciseMapper;
import org.exoplatform.addon.elearning.storage.ExerciseDao;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

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
}
