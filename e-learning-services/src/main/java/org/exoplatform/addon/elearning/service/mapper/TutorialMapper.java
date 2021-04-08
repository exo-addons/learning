package org.exoplatform.addon.elearning.service.mapper;

import org.exoplatform.addon.elearning.entities.StepEntity;
import org.exoplatform.addon.elearning.entities.TutorialEntity;
import org.exoplatform.addon.elearning.service.dto.Step;
import org.exoplatform.addon.elearning.service.dto.Tutorial;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class TutorialMapper {
    public static Tutorial convertTutorialToDTO(TutorialEntity tutorialEntity) {
        Tutorial tutorial = new Tutorial();
        tutorial.setAuthorId(tutorialEntity.getAuthorId());
        tutorial.setCreatedDate(new Date(tutorialEntity.getCreatedDate().getTime()));
        tutorial.setId(tutorialEntity.getId());
        tutorial.setStatus(tutorialEntity.getStatus());
        tutorial.setDescription(tutorialEntity.getDescription());
        tutorial.setTitle(tutorialEntity.getTitle());
        tutorial.setSteps(convertStepsToDTOs(tutorialEntity.getSteps()));
        return tutorial;
    }

    public static TutorialEntity convertTutorialToEntity(Tutorial tutorial) {
        TutorialEntity tutorialEntity = new TutorialEntity();
        tutorialEntity.setAuthorId(tutorial.getAuthorId());
        tutorialEntity.setCreatedDate(new Timestamp(tutorial.getCreatedDate().getTime()));
        tutorialEntity.setId(tutorial.getId());
        tutorialEntity.setStatus(tutorial.getStatus());
        tutorialEntity.setDescription(tutorial.getDescription());
        tutorialEntity.setTitle(tutorial.getTitle());
        tutorialEntity.setSteps(convertStepsToEntities(tutorial.getSteps()));
        return tutorialEntity;
    }

    private static Collection<StepEntity> convertStepsToEntities(Collection<Step> steps) {
        List<StepEntity> stepEntities = new ArrayList<>();
        for(Step step : steps) {
            stepEntities.add(convertStepToEntity(step));
        }
        return stepEntities;
    }

    private static List<Step> convertStepsToDTOs(Collection<StepEntity> stepEntities) {
        List<Step> steps = new ArrayList<>();
        for(StepEntity stepEntity : stepEntities) {
            steps.add(convertStepToDTO(stepEntity));
        }
        return steps;
    }

    public static StepEntity convertStepToEntity(Step step) {
        StepEntity stepEntity = new StepEntity();
        stepEntity.setMedia(step.getMedia());
        stepEntity.setId(step.getId());
        return stepEntity;
    }
    public static Step convertStepToDTO(StepEntity stepEntity) {
        Step step = new Step();
        step.setId(stepEntity.getId());
        step.setMedia(stepEntity.getMedia());
        return step;
    }
}
