package org.exoplatform.addon.elearning.storage.mapper;

import org.exoplatform.addon.elearning.dao.ThemeDao;
import org.exoplatform.addon.elearning.dto.Tutorial;
import org.exoplatform.addon.elearning.entity.ThemeEntity;
import org.exoplatform.addon.elearning.entity.TutorialEntity;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public class TutorialMapper {

  private TutorialMapper() {
  }

  public static Tutorial convertTutorialToDTO(TutorialEntity tutorialEntity) {
    if (tutorialEntity == null) {
      return null;
    }

    Tutorial tutorial = new Tutorial();
    tutorial.setAuthor(tutorialEntity.getAuthor());
    tutorial.setCreatedDate(new Date(tutorialEntity.getCreatedDate().getTime()));
    tutorial.setId(tutorialEntity.getId());
    tutorial.setStatus(tutorialEntity.getStatus());
    tutorial.setDescription(tutorialEntity.getDescription());
    tutorial.setTitle(tutorialEntity.getTitle());
    List<Long> themeIds = tutorialEntity.getThemeEntities().stream().map(ThemeEntity::getId).collect(Collectors.toList());
    tutorial.setThemeIds(themeIds);
    return tutorial;
  }

  public static List<Tutorial> convertTutorialsToDTOs(Set<TutorialEntity> tutorialEntities) {
    if (tutorialEntities == null) {
      return new ArrayList<>();
    }
    return tutorialEntities.stream().map(TutorialMapper::convertTutorialToDTO).collect(Collectors.toList());
  }

  public static TutorialEntity convertTutorialToEntity(Tutorial tutorial, ThemeDao themeDao) {
    if (tutorial == null) {
      return null;
    }

    TutorialEntity tutorialEntity = new TutorialEntity();
    tutorialEntity.setAuthor(tutorial.getAuthor());
    tutorialEntity.setCreatedDate(tutorial.getCreatedDate() != null ? new Timestamp(tutorial.getCreatedDate().getTime()) : new Timestamp(System.currentTimeMillis()));
    tutorialEntity.setId(tutorial.getId() == null || tutorial.getId().equals(0L) ? null : tutorial.getId());
    tutorialEntity.setStatus(tutorial.getStatus());
    tutorialEntity.setDescription(tutorial.getDescription());
    tutorialEntity.setTitle(tutorial.getTitle());
    Set<ThemeEntity> themeEntities = new HashSet<>();
    for (Long themeId : tutorial.getThemeIds()) {
      tutorialEntity.addThemeEntity(themeDao.find(themeId));
    }
    
    for (ThemeEntity themeEntity : themeEntities) {
    }
    return tutorialEntity;
  }

  public static Set<TutorialEntity> convertTutorialsToEntities(List<Tutorial> tutorials, ThemeDao themeDao) {
    if (tutorials == null) {
      return new HashSet<>();
    }
    return new HashSet<>(tutorials.stream().map(tutorial -> convertTutorialToEntity(tutorial, themeDao)).collect(Collectors.toList()));
  }

}
