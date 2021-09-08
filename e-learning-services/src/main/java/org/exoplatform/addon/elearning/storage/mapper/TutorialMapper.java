package org.exoplatform.addon.elearning.storage.mapper;

import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.dto.Tutorial;
import org.exoplatform.addon.elearning.entity.TutorialEntity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TutorialMapper {

  public TutorialMapper() {
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
    tutorial.setThemes(ThemeMapper.convertThemesToDTOs(tutorialEntity.getThemeEntities()));
    return tutorial;
  }

  public static List<Tutorial> convertTutorialsToDTOs(Set<TutorialEntity> tutorialEntities) {
    if (tutorialEntities == null) {
      return null;
    }

    return tutorialEntities.stream().map(TutorialMapper::convertTutorialToDTO).collect(Collectors.toList());
  }

  public static TutorialEntity convertTutorialToEntity(Tutorial tutorial) {
    if (tutorial == null) {
      return null;
    }

    TutorialEntity tutorialEntity = new TutorialEntity();
    tutorialEntity.setAuthor(tutorial.getAuthor());
    tutorialEntity.setCreatedDate(tutorial.getCreatedDate() != null ? new Timestamp(tutorial.getCreatedDate().getTime()) : new Timestamp(System.currentTimeMillis()));
    tutorialEntity.setId(tutorial.getId());
    tutorialEntity.setStatus(tutorial.getStatus());
    tutorialEntity.setDescription(tutorial.getDescription());
    tutorialEntity.setTitle(tutorial.getTitle());
    tutorialEntity.setThemeEntities(ThemeMapper.convertThemesToEntities((List<Theme>) tutorial.getThemes()));
    return tutorialEntity;
  }

  public static Set<TutorialEntity> convertTutorialsToEntities(List<Tutorial> tutorials) {
    if (tutorials == null) {
      return null;
    }

    return (Set<TutorialEntity>) tutorials.stream().map(TutorialMapper::convertTutorialToEntity).collect(Collectors.toList());
  }

}
