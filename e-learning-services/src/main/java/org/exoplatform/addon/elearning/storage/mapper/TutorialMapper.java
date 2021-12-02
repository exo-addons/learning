package org.exoplatform.addon.elearning.storage.mapper;

import org.exoplatform.addon.elearning.dao.StepDAO;
import org.exoplatform.addon.elearning.dao.ThemeDao;
import org.exoplatform.addon.elearning.dto.Tutorial;
import org.exoplatform.addon.elearning.entity.Status;
import org.exoplatform.addon.elearning.entity.StepEntity;
import org.exoplatform.addon.elearning.entity.ThemeEntity;
import org.exoplatform.addon.elearning.entity.TutorialEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TutorialMapper {

  private TutorialMapper() {
  }

  public static Tutorial convertTutorialToDTO(TutorialEntity tutorialEntity) {
    if (tutorialEntity == null) {
      return null;
    }

    Tutorial tutorial = new Tutorial();
    tutorial.setId(tutorialEntity.getId());
    tutorial.setTitle(tutorialEntity.getTitle());
    tutorial.setDescription(tutorialEntity.getDescription());
    tutorial.setAuthor(tutorialEntity.getAuthor());
    tutorial.setCreatedDate(new Date(tutorialEntity.getCreatedDate().getTime()));
    tutorial.setLastModifiedDate(tutorialEntity.getLastModifiedDate());
    List<Long> themeIds = tutorialEntity.getThemeEntities().stream().map(ThemeEntity::getId).collect(Collectors.toList());
    tutorial.setThemeIds(themeIds);
    tutorial.setStatus(tutorialEntity.getStatus().name());
    List<Long> stepsIds = tutorialEntity.getStepEntities().stream().map(StepEntity::getId).collect(Collectors.toList());
    tutorial.setStepsIds(stepsIds);
    return tutorial;
  }

  public static List<Tutorial> convertTutorialsToDTOs(List<TutorialEntity> tutorialEntities) {
    if (tutorialEntities == null) {
      return new ArrayList<>();
    }
    return tutorialEntities.stream().map(TutorialMapper::convertTutorialToDTO).collect(Collectors.toList());
  }

  public static TutorialEntity convertTutorialToEntity(Tutorial tutorial, ThemeDao themeDao, StepDAO stepDAO) {
    if (tutorial == null) {
      return null;
    }

    TutorialEntity tutorialEntity = new TutorialEntity();
    tutorialEntity.setId(tutorial.getId() == null || tutorial.getId().equals(0L) ? null : tutorial.getId());
    tutorialEntity.setTitle(tutorial.getTitle());
    tutorialEntity.setDescription(tutorial.getDescription());
    tutorialEntity.setAuthor(tutorial.getAuthor());
    tutorialEntity.setCreatedDate(tutorial.getCreatedDate() != null ? new Timestamp(tutorial.getCreatedDate().getTime()) : new Timestamp(System.currentTimeMillis()));
    tutorialEntity.setLastModifiedDate(tutorial.getLastModifiedDate());
    for (Long themeId : tutorial.getThemeIds()) {
      tutorialEntity.addThemeEntity(themeDao.find(themeId));
    }
    tutorialEntity.setStatus(Status.valueOf(tutorial.getStatus().toUpperCase()));
    List<StepEntity> stepEntities = new ArrayList<>();
    for (Long stepId : tutorial.getStepsIds()) {
      stepEntities.add(stepDAO.find(stepId));
    }
    tutorialEntity.setStepEntities(stepEntities);
    return tutorialEntity;
  }

  public static List<TutorialEntity> convertTutorialsToEntities(List<Tutorial> tutorials, ThemeDao themeDao, StepDAO stepDAO) {
    if (tutorials == null) {
      return new ArrayList<>();
    }
    return tutorials.stream().map(tutorial -> convertTutorialToEntity(tutorial, themeDao, stepDAO)).collect(Collectors.toList());
  }

}
