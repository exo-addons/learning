package org.exoplatform.addon.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.exoplatform.services.security.Identity;
import org.exoplatform.services.security.MembershipEntry;

import java.io.Serializable;
import java.util.ArrayList;
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

  private Set<String> managers = new HashSet<>();

  private Set<String> participators = new HashSet<>();

  private Long parentId;

  private List<Long> childrenIds = new ArrayList<>();

  private Long lastModifiedDate;

  private List<Long> tutorialIds = new ArrayList<>();
  
  private String creator;

  public Theme(String name, String spaceName, Set<String> managers, Set<String> participators, String creator) {
    this.name = name;
    this.spaceName = spaceName;
    this.managers = managers;
    this.participators = participators;
    this.creator = creator;
  }

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
