package org.exoplatform.addon.elearning.service.mapper;

import org.exoplatform.addon.elearning.entities.TutorialEntity;
import org.exoplatform.addon.elearning.service.dto.Tutorial;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    ArrayList<Long> themeIds = new ArrayList<Long>(tutorialEntity.getThemeIds());
    tutorial.setThemeIds(themeIds);
    return tutorial;
  }

  public static List<Tutorial> convertTutorialsToDTOs(List<TutorialEntity> tutos) {

    return tutos.stream().map(TutorialMapper::convertTutorialToDTO).collect(Collectors.toList());
  }

  public static TutorialEntity convertTutorialToEntity(Tutorial tutorial) {
    TutorialEntity tutorialEntity = new TutorialEntity();
    tutorialEntity.setAuthor(tutorial.getAuthor());
    tutorialEntity.setCreatedDate(new Timestamp(tutorial.getCreatedDate().getTime()));
    tutorialEntity.setId(tutorial.getId());
    tutorialEntity.setStatus(tutorial.getStatus());
    tutorialEntity.setDescription(tutorial.getDescription());
    tutorialEntity.setTitle(tutorial.getTitle());
    tutorialEntity.setThemeIds(tutorial.getThemeIds());
    return tutorialEntity;
  }

}
