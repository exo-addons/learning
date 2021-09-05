package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.dao.ThemeDao;
import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.entity.ThemeEntity;
import org.exoplatform.addon.elearning.storage.mapper.ThemeMapper;

import java.util.List;

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

  public List<Theme> getAllThemes() {
    List<ThemeEntity> themeEntities = themeDao.findAll();
    return ThemeMapper.convertThemesToDTOs(themeEntities);
  }

  public List<Theme> findAllThemesByName(String themeName) {
    List<ThemeEntity> themeEntities = themeDao.findAllThemesByName(themeName);
    return ThemeMapper.convertThemesToDTOs(themeEntities);
  }
}