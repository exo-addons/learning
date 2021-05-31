package org.exoplatform.addon.elearning.service.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.exoplatform.addon.elearning.entities.ThemeEntity;
import org.exoplatform.addon.elearning.service.dto.Theme;
import org.exoplatform.addon.elearning.service.mapper.ThemeMapper;
import org.exoplatform.addon.elearning.storage.ThemeDao;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.services.rest.resource.ResourceContainer;

public class ThemeService implements ResourceContainer {

  private ThemeDao themeDao;

  public ThemeService(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  @ExoTransactional
  public Theme createTheme(Theme theme) {
    ThemeEntity themeEntity = new ThemeEntity();
    try {
      themeEntity = themeDao.create(ThemeMapper.convertThemeToEntity(theme));
    } catch (Exception e) {
      Logger LOGGER = Logger.getLogger("LOG");
      LOGGER.info("Could not create Theme ERROR - " + e);
    }
    return ThemeMapper.convertThemeToDTO(themeEntity);
  }

  @ExoTransactional
  public void deleteTheme(Long id) {
    ThemeEntity theme = new ThemeEntity();
    try {
      theme = themeDao.find(id);

    } catch (Exception e) {
      Logger LOGGER = Logger.getLogger("LOG");
      LOGGER.info("Could not delete Theme ERROR - " + e);
    }
    themeDao.delete(theme);
  }

  @ExoTransactional
  public Theme updateTheme(Theme theme) {
    ThemeEntity themeEntity = new ThemeEntity();
    try {
      themeEntity = themeDao.find(theme.getId());
      themeEntity.setName(theme.getName());

    } catch (Exception e) {
      Logger LOGGER = Logger.getLogger("LOG");
      LOGGER.info("Could not update Theme ERROR - " + e);
    }
    return ThemeMapper.convertThemeToDTO(themeEntity);
  }

  public List<Theme> getAllThemes(int offset, int limit) {
    List<ThemeEntity> themes = new ArrayList<ThemeEntity>();

    try {
      themes = themeDao.findAll();

    } catch (Exception e) {
      Logger LOGGER = Logger.getLogger("LOG");
      LOGGER.info("Could not get all Themes ERROR - " + e);
    }
    return ThemeMapper.convertThemesToDTOs(themes);
  }

  public Theme getThemeById(Long id) {
    ThemeEntity theme = new ThemeEntity();
    try {
      theme = themeDao.find(id);

    } catch (Exception e) {
      Logger LOGGER = Logger.getLogger("LOG");
      LOGGER.info("Could not get Theme with id " + id + " ERROR - " + e);
    }
    return ThemeMapper.convertThemeToDTO(theme);
  }

  public List<String> getAllThemeNames(int offset, int limit) {
    List<String> themes = new ArrayList<String>();

    try {
      themes = themeDao.getAllThemeNames();

    } catch (Exception e) {
      Logger LOGGER = Logger.getLogger("LOG");
      LOGGER.info("Could not get all Theme Names ERROR - " + e);
    }
    return themes;
  }
}
