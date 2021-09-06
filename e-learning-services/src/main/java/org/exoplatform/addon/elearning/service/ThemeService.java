package org.exoplatform.addon.elearning.service;

import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.storage.ThemeStorage;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.services.rest.resource.ResourceContainer;

import java.util.List;
import java.util.Set;

public class ThemeService implements ResourceContainer {

  private ThemeStorage themeStorage;

  public ThemeService(ThemeStorage themeStorage) {
    this.themeStorage = themeStorage;
  }

  @ExoTransactional
  public Theme createTheme(Theme theme) throws IllegalAccessException {
    if (theme == null) {
      throw new IllegalAccessException("Theme is mandatory");
    }

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
}
