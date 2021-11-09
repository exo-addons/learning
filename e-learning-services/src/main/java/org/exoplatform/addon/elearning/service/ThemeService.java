package org.exoplatform.addon.elearning.service;

import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.storage.ThemeStorage;
import org.exoplatform.addon.elearning.util.UserUtil;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.social.core.space.spi.SpaceService;

import java.util.ArrayList;
import java.util.List;

public class ThemeService implements ResourceContainer {

  private static final Log LOG = ExoLogger.getLogger(ThemeService.class);

  public static final String DEFAULT_USERS_GROUP = "/platform/users";

  public static final String DEFAULT_USERS_PERMISSION = "*:" + DEFAULT_USERS_GROUP;

  private ThemeStorage themeStorage;

  private SpaceService spaceService;

  public ThemeService(ThemeStorage themeStorage, SpaceService spaceService) {
    this.themeStorage = themeStorage;
    this.spaceService = spaceService;
  }

  public void deleteTheme(Long id) {
    themeStorage.deleteThemeById(id);
  }

  public Theme updateTheme(Theme theme) {
    theme.setLastModifiedDate(System.currentTimeMillis());
    return themeStorage.updateTheme(theme);
  }

  public List<Theme> getAllThemes(int offset, int limit) {
    return themeStorage.getAllThemes();
  }

  public Theme getThemeById(Long id) {
    return themeStorage.getThemeById(id);
  }

  public List<Theme> findAllThemesByName(String themeName, int offset, int limit) {
    return themeStorage.findAllThemesByName(themeName);
  }

  public Theme createTheme(Theme theme, Long parentId, String spaceName) {
    List<String> managers = new ArrayList<>();
    List<String> participators = new ArrayList<>();
    if (parentId != null) {
      Theme parentTheme = themeStorage.getThemeById(parentId);
      theme.setParentId(parentId);
      List<String> parentThemeManagers = parentTheme.getManagers();
      List<String> parentThemeParticipators = parentTheme.getParticipators();
      managers.addAll(parentThemeManagers);
      participators.addAll(parentThemeParticipators);
    } else if (spaceName != null) {
      Space space = spaceService.getSpaceByPrettyName(spaceName);
      theme.setSpaceName(spaceName);
      List<String> memberships = UserUtil.getSpaceMemberships(space.getGroupId());
      managers.add(memberships.get(0));
      participators.add(memberships.get(1));
    } else {
      managers.add(theme.getCreator());
      participators.add(DEFAULT_USERS_PERMISSION);
    }

    theme.setManagers(managers);
    theme.setParticipators(participators);
    theme.setLastModifiedDate(System.currentTimeMillis());

    theme = themeStorage.createTheme(theme);
    return theme;
  }

  public List<Theme> findThemesBySpaceName(String spaceName, boolean isRoot, String query, int offset, int limit) {
    return themeStorage.findThemesBySpaceName(spaceName, isRoot, query, offset, limit);
  }

  public long countFoundThemesBySpaceName(String spaceName, boolean isRoot, String query) {
    return themeStorage.countFoundThemesBySpaceName(spaceName, isRoot, query);
  }

  public long countParentThemeChildren(long parentThemeId, String query) {
    return themeStorage.countParentThemeChildren(parentThemeId, query);
  }

  public List<Theme> retrieveChildThemes(long parentThemeId, String query, int offset, int limit) {
    return themeStorage.retrieveChildThemes(parentThemeId, query, offset, limit);
  }
}
