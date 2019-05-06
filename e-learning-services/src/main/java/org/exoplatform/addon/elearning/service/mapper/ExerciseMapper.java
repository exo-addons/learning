package org.exoplatform.addon.elearning.service.mapper;

import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.addon.elearning.entities.ExamEntity;
import org.exoplatform.addon.elearning.entities.ExerciseEntity;
import org.exoplatform.addon.elearning.service.dto.ExerciseDTO;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExerciseMapper {
  protected IdentityManager identityManager = null;

  public ExerciseMapper() {
    identityManager = CommonsUtils.getService(IdentityManager.class);
  }
  public ExerciseDTO exerciseToExerciseDTO(ExerciseEntity exercise){
    return new ExerciseDTO(exercise);
  }
  public List<ExerciseDTO> exercisesToExerciseDTOs(List<ExerciseEntity>exercices){
    return exercices.stream()
                  .filter(Objects::nonNull)
                  .map(this::exerciseToExerciseDTO)
                  .collect(Collectors.toList());
  }
  public ExerciseEntity exerciseDTOToExercise(ExerciseDTO exerciseDTO){
    try {
      if(exerciseDTO==null){
        return null;
      }else{
        String user= ConversationState.getCurrent().getIdentity().getUserId();
        ExerciseEntity exercise=new ExerciseEntity();
        exercise.setQuestionExercise(exerciseDTO.getQuestionExercise());
        exercise.setUserName(user);
        exercise.setAnswerExercise(exerciseDTO.getAnswerExercise());
        exercise.setScaleExercise(exerciseDTO.getScaleExercise());
        exercise.setChoose1(exerciseDTO.getChoose1());
        exercise.setChoose2(exerciseDTO.getChoose2());
        exercise.setChoose3(exerciseDTO.getChoose3());
        CourseEntity course=this.exerciseCoursFromLongId(exerciseDTO.getIdCourse());
        exercise.setCourse(course);
        ExamEntity exam=this.exerciseExamFromLongId(exerciseDTO.getIdExam());
        exercise.setExam(exam);
        return exercise;

      }

    }catch(Exception pe){
      pe.printStackTrace();
    }
    return null;
  }

  public CourseEntity exerciseCoursFromLongId(Long idCourse) {
    CourseEntity course=new CourseEntity();
    course.setIdCourse(idCourse);
    return course;
  }
  public ExamEntity exerciseExamFromLongId(Long idExam) {
    ExamEntity exam=new ExamEntity();
    exam.setIdExam(idExam);
    return exam;
  }

}
