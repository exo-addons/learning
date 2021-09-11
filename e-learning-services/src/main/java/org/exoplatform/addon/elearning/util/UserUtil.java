package org.exoplatform.addon.elearning.util;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.portal.config.UserACL;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.security.Identity;
import org.exoplatform.services.security.MembershipEntry;

import java.util.*;

public class UserUtil {

  private UserUtil() {
  }

  public static String getDisplayName(User user) {
    if (user == null) {
      return "";
    }
    String displayName = user.getDisplayName();
    if (displayName == null) {
      if (user.getFirstName() != null) {
        displayName = user.getFirstName();
      }
      if (user.getLastName() != null) {
        if (displayName == null) {
          displayName = user.getLastName();
        } else {
          displayName = new StringBuffer(displayName).append(" " + user.getLastName()).toString();
        }
      }
      if (displayName == null) {
        displayName = user.getUserName();
      }
    }
    return displayName;
  }

  public static List<String> getSpaceMemberships(String spaceGroupId) {
    List<String> memberships = new LinkedList<>();
    if (spaceGroupId != null) {
      memberships.add(new MembershipEntry(spaceGroupId, "manager").toString());
      memberships.add(new MembershipEntry(spaceGroupId, "member").toString());
    } else {
      throw new IllegalArgumentException("spaceGroupId is null");
    }
    return memberships;
  }

  public static List<String> getMemberships(Identity identity) {
    Map<String, List<MembershipEntry>> gms = new HashMap<>();
    for (MembershipEntry m : identity.getMemberships()) {
      List<MembershipEntry> ms = gms.get(m.getGroup());
      if (ms == null) {
        ms = new LinkedList<MembershipEntry>();
        gms.put(m.getGroup(), ms);
        //
        ms.add(new MembershipEntry(m.getGroup(), MembershipEntry.ANY_TYPE));
      }
      if (!m.getMembershipType().equals(MembershipEntry.ANY_TYPE)) {
        ms.add(m);
      }
    }

    List<String> memberships = new ArrayList<>();
    String userName = identity.getUserId();
    memberships.add(userName);
    for (List<MembershipEntry> ms : gms.values()) {
      for (MembershipEntry m : ms) {
        memberships.add(m.toString());
      }
    }
    return memberships;
  }

  public static boolean isPlatformAdmin(Identity identity) {
    UserACL userACL = CommonsUtils.getService(UserACL.class);
    String adminGroup = userACL.getAdminGroups();
    //
    for (MembershipEntry m : identity.getMemberships()) {
      String group = m.getGroup();
      if (group.equals(adminGroup)) {
        return true;
      }
    }
    return false;
  }
}
