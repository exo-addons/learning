package org.exoplatform.addon.elearning.storage.mapper;

import org.exoplatform.addon.elearning.dao.ThemeDao;
import org.exoplatform.addon.elearning.dao.TutorialDao;
import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.entity.ThemeEntity;
import org.exoplatform.addon.elearning.entity.TutorialEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    theme.setLastModifiedDate(themeEntity.getLastModifiedDate());
    theme.setCreator(themeEntity.getCreator());
    List<Long> tutorialIds = themeEntity.getTutorialEntities().stream().map(TutorialEntity::getId).collect(Collectors.toList());
    theme.setTutorialIds(tutorialIds);
    if (themeEntity.getParent() != null) {
      theme.setParentId(themeEntity.getParent().getId());
    }
    return theme;
  }

  public static List<Theme> convertThemeEntitiesToDTOs(Set<ThemeEntity> themeEntities, boolean withChildren) {
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
    if (theme.getChildrenIds() != null && !theme.getChildrenIds().isEmpty()) {
      Set<ThemeEntity> children = new HashSet<>();
      for (Long childId : theme.getChildrenIds()) {
        children.add(themeDao.find(childId));
      }
      themeEntity.setChildren(children);
    }
    themeEntity.setLastModifiedDate(theme.getLastModifiedDate());
    themeEntity.setCreator(theme.getCreator());
    for (Long tutorialId : theme.getTutorialIds()) {
      themeEntity.addTutorialEntity(tutorialDao.find(tutorialId));
    }
    if (theme.getParentId() != null) {
      themeEntity.setParent(themeDao.find(theme.getParentId()));
    }
    return themeEntity;
  }

  public static Set<ThemeEntity> convertThemeDTOsToEntities(List<Theme> themes, ThemeDao themeDao, TutorialDao tutorialDao) {
    if (themes == null) {
      return new HashSet<>();
    }
    List<ThemeEntity> themeEntities = new ArrayList<>();
    for (Theme theme : themes) {
      ThemeEntity themeEntity = convertThemeDTOToEntity(theme, themeDao, tutorialDao);
      if (theme != null && theme.getParentId() != null) {
        themeEntity.setParent(themeDao.find(theme.getParentId()));
      }
      themeEntities.add(themeEntity);
    }
    return new HashSet<>(themeEntities);
  }

}
