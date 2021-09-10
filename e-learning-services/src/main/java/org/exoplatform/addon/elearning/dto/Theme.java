package org.exoplatform.addon.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.exoplatform.services.security.Identity;
import org.exoplatform.services.security.MembershipEntry;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theme implements Serializable {
  private Long id;

  private String name;

  private String spaceName;

  private Set<String> managers;

  private Set<String> participators;

  private Theme parent;

  private List<Theme> children;

  private Long lastModifiedDate;

  private List<Tutorial> tutorials;

  public boolean canEdit(Identity user) {
    return hasPermission(user, getManagers());
  }

  private boolean hasPermission(Identity user, Set<String> permissions) {
    if (permissions.contains(user.getUserId())) {
      return true;
    } else {
      Set<MembershipEntry> memberships = new HashSet<MembershipEntry>();
      for (String per : permissions) {
        MembershipEntry entry = MembershipEntry.parse(per);
        if (entry != null) {
          memberships.add(entry);
        }
      }

      for (MembershipEntry entry : user.getMemberships()) {
        if (memberships.contains(entry)) {
          return true;
        }
      }
    }

    return false;
  }

}
