package org.exoplatform.addon.elearning.service;

import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.service.exception.EntityNotFoundException;
import org.exoplatform.addon.elearning.storage.ThemeStorage;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import java.util.HashSet;
import java.util.Set;

public class ThemeService implements ResourceContainer {

  private static final Log LOG = ExoLogger.getLogger(ThemeService.class);

  private ThemeStorage themeStorage;

  public ThemeService(ThemeStorage themeStorage) {
    this.themeStorage = themeStorage;
  }

  @ExoTransactional
  public Theme createTheme(Theme theme) throws IllegalAccessException {
    if (theme == null) {
      throw new IllegalAccessException("Theme is mandatory");
    }
    theme.setLastModifiedDate(System.currentTimeMillis());

    return themeStorage.createTheme(theme);
  }

  @ExoTransactional
  public void deleteTheme(Long id) {
    themeStorage.deleteThemeById(id);
  }

  @ExoTransactional
  public Theme updateTheme(Theme theme) {
    return themeStorage.updateTheme(theme);
  }

  public Set<Theme> getAllThemes(int offset, int limit) {
    return themeStorage.getAllThemes();
  }

  public Theme getThemeById(Long id) {
    return themeStorage.getThemeById(id);
  }

  public Set<Theme> findAllThemesByName(String themeName, int offset, int limit) {
    return themeStorage.findAllThemesByName(themeName);
  }

  public Theme createTheme(Theme theme, Long parentId) throws IllegalAccessException, EntityNotFoundException {
    Theme parentTheme = themeStorage.getThemeById(parentId);
    if (parentTheme != null) {
      theme.setParent(parentTheme);
      theme.setParticipators(new HashSet<>(parentTheme.getParticipators()));
      theme.setManagers(new HashSet<>(parentTheme.getManagers()));
      theme.setLastModifiedDate(System.currentTimeMillis());

      theme = createTheme(theme);
      return theme;
    } else {
      LOG.info("Can not find theme for parent with ID: " + parentId);
      throw new EntityNotFoundException(parentId, Theme.class);
    }
  }
}
