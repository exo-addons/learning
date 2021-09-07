package org.exoplatform.addon.elearning.storage.mapper;

import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.dto.Tutorial;
import org.exoplatform.addon.elearning.entity.ThemeEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ThemeMapper {

  public ThemeMapper() {
  }

  public static Theme convertThemeToDTO(ThemeEntity themeEntity) {
    if (themeEntity == null) {
      return null;
    }

    Theme theme = new Theme();
    theme.setName(themeEntity.getName());
    theme.setId(themeEntity.getId());
    theme.setSpaceName(themeEntity.getSpaceName());
    theme.setManagers(themeEntity.getManagers());
    theme.setParticipators(themeEntity.getParticipators());
    theme.setParent(convertThemeToDTO(themeEntity.getParent()));
    theme.setChildren((List<Theme>) convertThemesToDTOs(themeEntity.getChildren()));
    theme.setLastModifiedDate(themeEntity.getLastModifiedDate());
    theme.setTutorialEntities((List<Tutorial>) TutorialMapper.convertTutorialsToDTOs(themeEntity.getTutorialEntities()));
    return theme;
  }

  public static Set<Theme> convertThemesToDTOs(Set<ThemeEntity> themes) {

    return (Set<Theme>) themes.stream().map(ThemeMapper::convertThemeToDTO).collect(Collectors.toList());
  }

  public static ThemeEntity convertThemeToEntity(Theme theme) {
    if (theme == null) {
      return null;
    }

    ThemeEntity themeEntity = new ThemeEntity();
    themeEntity.setId(theme.getId());
    themeEntity.setName(theme.getName());
    themeEntity.setSpaceName(theme.getSpaceName());
    themeEntity.setManagers(theme.getManagers());
    themeEntity.setParticipators(theme.getParticipators());
    themeEntity.setParent(convertThemeToEntity(theme.getParent()));
    themeEntity.setChildren(convertThemesToEntities((Set<Theme>) theme.getChildren()));
    themeEntity.setLastModifiedDate(theme.getLastModifiedDate());
    themeEntity.setTutorialEntities(TutorialMapper.convertTutorialsToEntities((Set<Tutorial>) theme.getTutorialEntities()));
    return themeEntity;
  }

  public static Set<ThemeEntity> convertThemesToEntities(Set<Theme> themes) {

    return (Set<ThemeEntity>) themes.stream().map(ThemeMapper::convertThemeToEntity).collect(Collectors.toList());
  }

}
