package org.exoplatform.addon.elearning.storage.mapper;

import org.exoplatform.addon.elearning.dto.Step;
import org.exoplatform.addon.elearning.entity.StepEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StepMapper {

  public StepMapper() {
  }

  public static Step convertStepToDTO(StepEntity stepEntity) {
    if (stepEntity == null) {
      return null;
    }

    Step step = new Step();
    step.setId(stepEntity.getId());
    step.setTitle(stepEntity.getTitle());
    step.setBody(stepEntity.getBody());
    step.setMedia(stepEntity.getMedia());
    step.setOrder(stepEntity.getOrder());
    step.setTutorial(TutorialMapper.convertTutorialToDTO(stepEntity.getTutorialEntity()));
    return step;

  }

  public static StepEntity convertStepToEntity(Step step) {
    if (step == null) {
      return null;
    }

    StepEntity stepEntity = new StepEntity();
    stepEntity.setId(step.getId());
    stepEntity.setTitle(step.getTitle());
    stepEntity.setBody(step.getBody());
    stepEntity.setMedia(step.getMedia());
    stepEntity.setOrder(step.getOrder());
    stepEntity.setTutorialEntity(TutorialMapper.convertTutorialToEntity(step.getTutorial()));
    return stepEntity;

  }

  public static List<Step> convertStepsToDTOs(List<StepEntity> stepEntities) {
    if (stepEntities == null) {
      return new ArrayList<>();
    }
    return stepEntities.stream().map(StepMapper::convertStepToDTO).collect(Collectors.toList());

  }

  public static List<StepEntity> convertStepsToEntities(List<Step> steps) {
    if (steps == null) {
      return new ArrayList<>();
    }
    return steps.stream().map(StepMapper::convertStepToEntity).collect(Collectors.toList());

  }

}
