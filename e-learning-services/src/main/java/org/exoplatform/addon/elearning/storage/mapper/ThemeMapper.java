package org.exoplatform.addon.elearning.storage.mapper;

import org.exoplatform.addon.elearning.entity.ThemeEntity;
import org.exoplatform.addon.elearning.dto.Theme;

import java.util.List;
import java.util.stream.Collectors;

public class ThemeMapper {

  public ThemeMapper() {
  }

  public static Theme convertThemeToDTO(ThemeEntity themeEntity) {
    Theme theme = new Theme();
    theme.setName(themeEntity.getName());
    theme.setId(themeEntity.getId());
    theme.setSpaceName(themeEntity.getSpaceName());
    return theme;
  }

  public static List<Theme> convertThemesToDTOs(List<ThemeEntity> themes) {

    return themes.stream().map(ThemeMapper::convertThemeToDTO).collect(Collectors.toList());
  }

  public static ThemeEntity convertThemeToEntity(Theme theme) {
    ThemeEntity themeEntity = new ThemeEntity();
    themeEntity.setId(theme.getId());
    themeEntity.setName(theme.getName());
    themeEntity.setSpaceName(theme.getSpaceName());
    return themeEntity;
  }

}
