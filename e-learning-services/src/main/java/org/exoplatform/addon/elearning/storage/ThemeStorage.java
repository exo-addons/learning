package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.dao.ThemeDao;
import org.exoplatform.addon.elearning.dao.TutorialDao;
import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.entity.ThemeEntity;
import org.exoplatform.addon.elearning.storage.mapper.ThemeMapper;
import org.exoplatform.commons.api.persistence.ExoTransactional;

import java.util.List;

public class ThemeStorage {
  private ThemeDao themeDao;
  private TutorialDao tutorialDao;

  public ThemeStorage(ThemeDao themeDao, TutorialDao tutorialDao) {
    this.themeDao = themeDao;
    this.tutorialDao = tutorialDao;
  }

  @ExoTransactional
  public Theme createTheme(Theme theme) {
    if (theme.getParentId() != null && theme.getParentId() > 0) {
      ThemeEntity parentTheme = themeDao.find(theme.getParentId());
      parentTheme.setLastModifiedDate(System.currentTimeMillis());
      themeDao.update(parentTheme);
    }
    ThemeEntity themeEntity = themeDao.create(ThemeMapper.convertThemeDTOToEntity(theme, themeDao, tutorialDao));
    return ThemeMapper.convertThemeEntityToDTO(themeEntity);
  }

  public Theme getThemeById(Long id) {
    ThemeEntity theme = themeDao.find(id);
    return ThemeMapper.convertThemeEntityToDTO(theme);
  }

  public void deleteThemeById(Long id) {
    ThemeEntity themeEntity = themeDao.find(id);
    themeDao.delete(themeEntity);
  }

  public Theme updateTheme(Theme theme) {
    ThemeEntity themeEntity = ThemeMapper.convertThemeDTOToEntity(theme, themeDao, tutorialDao);
    return ThemeMapper.convertThemeEntityToDTO(themeDao.update(themeEntity));
  }

  public List<Theme> getAllThemes() {
    List<ThemeEntity> themeEntities = themeDao.findAll();
    return ThemeMapper.convertThemeEntitiesToDTOs(themeEntities, true);
  }

  public List<Theme> findAllThemesByName(String themeName) {
    List<ThemeEntity> themeEntities = themeDao.findAllThemesByName(themeName);
    return ThemeMapper.convertThemeEntitiesToDTOs(themeEntities, true);
  }

  public List<Theme> findThemesBySpaceName(String spaceName, boolean isRoot, String query, int offset, int limit) {
    List<ThemeEntity> themeEntities = themeDao.findThemesBySpaceName(spaceName, isRoot, query, offset, limit);
    return ThemeMapper.convertThemeEntitiesToDTOs(themeEntities, true);
  }

  public long countFoundThemesBySpaceName(String spaceName, boolean isRoot, String query) {
    return themeDao.countFoundThemesBySpaceName(spaceName, isRoot, query);
  }

  public long countParentThemeChildren(long parentThemeId, String query) {
    return themeDao.countParentThemeChildren(parentThemeId, query);
  }

  public List<Theme> retrieveChildThemes(long parentThemeId, String query, int offset, int limit) {
    List<ThemeEntity> themeEntities = themeDao.retrieveChildThemes(parentThemeId, query, offset, limit);
    return ThemeMapper.convertThemeEntitiesToDTOs(themeEntities, true);
  }
}
