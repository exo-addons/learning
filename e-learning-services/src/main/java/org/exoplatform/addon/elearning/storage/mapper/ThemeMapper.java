package org.exoplatform.addon.elearning.storage.mapper;

import org.exoplatform.addon.elearning.dao.ThemeDao;
import org.exoplatform.addon.elearning.dao.TutorialDao;
import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.entity.ThemeEntity;
import org.exoplatform.addon.elearning.entity.TutorialEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ThemeMapper {

  private ThemeMapper() {
  }

  public static Theme convertThemeEntityToDTO(ThemeEntity themeEntity) {
    if (themeEntity == null) {
      return null;
    }

    Theme theme = new Theme();
    theme.setId(themeEntity.getId());
    theme.setName(themeEntity.getName());
    theme.setSpaceName(themeEntity.getSpaceName());
    theme.setManagers(themeEntity.getManagers());
    theme.setParticipators(themeEntity.getParticipators());
    theme.setColor(themeEntity.getColor());
    if (themeEntity.getParent() != null) {
      theme.setParentId(themeEntity.getParent().getId());
    }
    theme.setChildrenIds(themeEntity.getChildren().stream().map(ThemeEntity::getId).collect(Collectors.toList()));
    theme.setLastModifiedDate(themeEntity.getLastModifiedDate());
    theme.setTutorialIds(themeEntity.getTutorialEntities().stream().map(TutorialEntity::getId).collect(Collectors.toList()));
    theme.setCreator(themeEntity.getCreator());
    return theme;
  }

  public static List<Theme> convertThemeEntitiesToDTOs(List<ThemeEntity> themeEntities, boolean withChildren) {
    if (themeEntities == null) {
      return new ArrayList<>();
    }
    List<Theme> themeList = new ArrayList<>();
    for (ThemeEntity themeEntity : themeEntities) {
      if (themeEntity != null) {
        Theme theme = convertThemeEntityToDTO(themeEntity);
        if (themeEntity.getParent() != null) {
          theme.setParentId(themeEntity.getParent().getId());
        }
        if (withChildren) {
          List<Long> childrenIds = themeEntity.getChildren().stream().map(ThemeEntity::getId).collect(Collectors.toList());
          theme.setChildrenIds(childrenIds);
        }
        themeList.add(theme);
      }
    }
    return themeList;
  }

  public static ThemeEntity convertThemeDTOToEntity(Theme theme, ThemeDao themeDao, TutorialDao tutorialDao) {
    if (theme == null) {
      return null;
    }

    ThemeEntity themeEntity = new ThemeEntity();
    themeEntity.setId(theme.getId());
    themeEntity.setName(theme.getName());
    themeEntity.setSpaceName(theme.getSpaceName());
    themeEntity.setManagers(theme.getManagers());
    themeEntity.setParticipators(theme.getParticipators());
    themeEntity.setColor(theme.getColor());
    if (theme.getParentId() != null) {
      themeEntity.setParent(themeDao.find(theme.getParentId()));
    }
    if (theme.getChildrenIds() != null && !theme.getChildrenIds().isEmpty()) {
      List<ThemeEntity> children = new ArrayList<>();
      for (Long childId : theme.getChildrenIds()) {
        children.add(themeDao.find(childId));
      }
      themeEntity.setChildren(children);
    }
    themeEntity.setLastModifiedDate(theme.getLastModifiedDate());
    for (Long tutorialId : theme.getTutorialIds()) {
      themeEntity.addTutorialEntity(tutorialDao.find(tutorialId));
    }
    themeEntity.setCreator(theme.getCreator());
    return themeEntity;
  }

  public static List<ThemeEntity> convertThemeDTOsToEntities(List<Theme> themes, ThemeDao themeDao, TutorialDao tutorialDao) {
    if (themes == null) {
      return new ArrayList<>();
    }
    List<ThemeEntity> themeEntities = new ArrayList<>();
    for (Theme theme : themes) {
      ThemeEntity themeEntity = convertThemeDTOToEntity(theme, themeDao, tutorialDao);
      if (theme != null && theme.getParentId() != null) {
        themeEntity.setParent(themeDao.find(theme.getParentId()));
      }
      themeEntities.add(themeEntity);
    }
    return themeEntities;
  }

}
