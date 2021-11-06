package org.exoplatform.addon.elearning.storage.mapper;

import org.exoplatform.addon.elearning.dao.ThemeDao;
import org.exoplatform.addon.elearning.dao.TutorialDao;
import org.exoplatform.addon.elearning.dto.Step;
import org.exoplatform.addon.elearning.entity.StepEntity;
import org.exoplatform.addon.elearning.entity.TutorialEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StepMapper {

  private StepMapper() {
  }

  public static Step convertStepToDTO(StepEntity stepEntity) {
    if (stepEntity == null) {
      return null;
    }

    Step step = new Step();
    step.setId(stepEntity.getId());
    step.setTitle(stepEntity.getTitle());
    step.setContent(stepEntity.getContent());
    step.setImageFileId(stepEntity.getImageFileId());
    step.setMediaLink(stepEntity.getMediaLink());
    step.setOrder(stepEntity.getOrder());
    step.setTutorialId(stepEntity.getTutorialEntity().getId());
    return step;

  }

  public static StepEntity convertStepToEntity(Step step, ThemeDao themeDao, TutorialDao tutorialDao) {
    if (step == null) {
      return null;
    }

    StepEntity stepEntity = new StepEntity();
    stepEntity.setId(step.getId());
    stepEntity.setTitle(step.getTitle());
    stepEntity.setContent(step.getContent());
    stepEntity.setImageFileId(step.getImageFileId());
    stepEntity.setMediaLink(step.getMediaLink());
    stepEntity.setOrder(step.getOrder());
    TutorialEntity tutorialEntity = tutorialDao.find(step.getTutorialId());
    stepEntity.setTutorialEntity(tutorialEntity);
    return stepEntity;

  }

  public static List<Step> convertStepsToDTOs(List<StepEntity> stepEntities) {
    if (stepEntities == null) {
      return new ArrayList<>();
    }
    return stepEntities.stream().map(StepMapper::convertStepToDTO).collect(Collectors.toList());

  }

  public static List<StepEntity> convertStepsToEntities(List<Step> steps, ThemeDao themeDao, TutorialDao tutorialDao) {
    if (steps == null) {
      return new ArrayList<>();
    }
    return steps.stream().map(step -> convertStepToEntity(step, themeDao, tutorialDao)).collect(Collectors.toList());

  }

}
