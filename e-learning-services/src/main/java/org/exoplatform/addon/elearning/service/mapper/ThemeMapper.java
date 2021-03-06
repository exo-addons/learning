package org.exoplatform.addon.elearning.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.exoplatform.addon.elearning.entities.ThemeEntity;
import org.exoplatform.addon.elearning.service.dto.Theme;

public class ThemeMapper {

  public ThemeMapper() {
  }

  public static Theme convertThemeToDTO(ThemeEntity themeEntity) {
    Theme theme = new Theme();
    theme.setName(themeEntity.getName());
    theme.setId(themeEntity.getId());
    return theme;
  }

  public static List<Theme> convertThemesToDTOs(List<ThemeEntity> themes) {

    return themes.stream().map(ThemeMapper::convertThemeToDTO).collect(Collectors.toList());
  }

  public static ThemeEntity convertThemeToEntity(Theme theme) {
    ThemeEntity themeEntity = new ThemeEntity();
    themeEntity.setId(theme.getId());
    themeEntity.setName(theme.getName());
    return themeEntity;
  }

}
