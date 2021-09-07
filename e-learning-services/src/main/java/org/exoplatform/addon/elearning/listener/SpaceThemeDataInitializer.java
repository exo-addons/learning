package org.exoplatform.addon.elearning.listener;

import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.service.ThemeService;
import org.exoplatform.addon.elearning.util.UserUtil;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.social.core.space.SpaceListenerPlugin;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.social.core.space.spi.SpaceLifeCycleEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpaceThemeDataInitializer extends SpaceListenerPlugin {

  private static final Log LOG = ExoLogger.getLogger(SpaceThemeDataInitializer.class);

  private ThemeService themeService;

  public SpaceThemeDataInitializer(ThemeService themeService) {
    this.themeService = themeService;
  }

  @Override
  public void spaceCreated(SpaceLifeCycleEvent spaceLifeCycleEvent) {
    try {
      Space space = spaceLifeCycleEvent.getSpace();
      String spaceGroupId = space.getGroupId();

      List<String> memberships = UserUtil.getSpaceMemberships(spaceGroupId);
      Set<String> managers = new HashSet<>(Arrays.asList(memberships.get(0)));
      Set<String> participators = new HashSet<>(Arrays.asList(memberships.get(1)));
      Theme theme = new Theme(null, space.getDisplayName(), space.getPrettyName(), managers, participators, null, null, null, null);
      themeService.createTheme(theme);
    } catch (Exception e) {
      LOG.error("Could not create Tutorial Theme:", e);
    }
  }

  @Override
  public void spaceRemoved(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void applicationActivated(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void applicationAdded(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void applicationDeactivated(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void applicationRemoved(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void grantedLead(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void joined(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void left(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void revokedLead(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void spaceRenamed(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void spaceDescriptionEdited(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void spaceAvatarEdited(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void spaceAccessEdited(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void addInvitedUser(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void addPendingUser(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }

  @Override
  public void spaceBannerEdited(SpaceLifeCycleEvent spaceLifeCycleEvent) {

  }
}
