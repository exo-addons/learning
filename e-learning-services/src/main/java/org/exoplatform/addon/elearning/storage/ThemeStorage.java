package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.dao.ThemeDao;
import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.entity.ThemeEntity;
import org.exoplatform.addon.elearning.storage.mapper.ThemeMapper;

import java.util.List;
import java.util.Set;

public class ThemeStorage {
  private ThemeDao themeDao;

  public ThemeStorage(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  public Theme createTheme(Theme theme) {
    ThemeEntity themeEntity = themeDao.create(ThemeMapper.convertThemeToEntity(theme));
    return ThemeMapper.convertThemeToDTO(themeEntity);
  }

  public Theme getThemeById(Long id) {
    ThemeEntity theme = themeDao.find(id);
    return ThemeMapper.convertThemeToDTO(theme);
  }

  public void deleteThemeById(Long id) {
    ThemeEntity themeEntity = themeDao.find(id);
    themeDao.delete(themeEntity);
  }

  public Theme updateTheme(Theme theme) {
    ThemeEntity themeEntity = ThemeMapper.convertThemeToEntity(theme);
    Theme updatedTheme = ThemeMapper.convertThemeToDTO(themeDao.update(themeEntity));
    return updatedTheme;
  }

  public Set<Theme> getAllThemes() {
    Set<ThemeEntity> themeEntities = (Set<ThemeEntity>) themeDao.findAll();
    return ThemeMapper.convertThemesToDTOs(themeEntities);
  }

  public Set<Theme> findAllThemesByName(String themeName) {
    Set<ThemeEntity> themeEntities = (Set<ThemeEntity>) themeDao.findAllThemesByName(themeName);
    return ThemeMapper.convertThemesToDTOs(themeEntities);
  }
}
