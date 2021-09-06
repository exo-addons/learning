package org.exoplatform.addon.elearning.storage.mapper;

import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.entity.ThemeEntity;
import org.exoplatform.addon.elearning.entity.TutorialEntity;
import org.exoplatform.addon.elearning.dto.Tutorial;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TutorialMapper {

  public TutorialMapper() {
  }

  public static Tutorial convertTutorialToDTO(TutorialEntity tutorialEntity) {
    Tutorial tutorial = new Tutorial();
    tutorial.setAuthor(tutorialEntity.getAuthor());
    tutorial.setCreatedDate(new Date(tutorialEntity.getCreatedDate().getTime()));
    tutorial.setId(tutorialEntity.getId());
    tutorial.setStatus(tutorialEntity.getStatus());
    tutorial.setDescription(tutorialEntity.getDescription());
    tutorial.setTitle(tutorialEntity.getTitle());
    Set<ThemeEntity> themeEntities = tutorialEntity.getThemeEntities();
    Set<Theme> themes = ThemeMapper.convertThemesToDTOs(themeEntities);
    tutorial.setThemes(themes);
    return tutorial;
  }

  public static List<Tutorial> convertTutorialsToDTOs(List<TutorialEntity> tutos) {

    return tutos.stream().map(TutorialMapper::convertTutorialToDTO).collect(Collectors.toList());
  }

  public static TutorialEntity convertTutorialToEntity(Tutorial tutorial) {
    TutorialEntity tutorialEntity = new TutorialEntity();
    tutorialEntity.setAuthor(tutorial.getAuthor());
    tutorialEntity.setCreatedDate(tutorial.getCreatedDate() != null ? new Timestamp(tutorial.getCreatedDate().getTime()) : new Timestamp(System.currentTimeMillis()));
    tutorialEntity.setId(tutorial.getId());
    tutorialEntity.setStatus(tutorial.getStatus());
    tutorialEntity.setDescription(tutorial.getDescription());
    tutorialEntity.setTitle(tutorial.getTitle());
    tutorialEntity.setThemeEntities(ThemeMapper.convertThemesToEntities(tutorial.getThemes()));
    return tutorialEntity;
  }

}
