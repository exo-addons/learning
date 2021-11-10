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

  private List<String> managers = new ArrayList<>();

  private List<String> participators = new ArrayList<>();

  private Long parentId;

  private List<Long> childrenIds = new ArrayList<>();

  private Long lastModifiedDate;

  private List<Long> tutorialIds = new ArrayList<>();

  private String creator;
  
  private List<Breadcrumb> breadcrumbs = new ArrayList<>();

  public Theme(String name, String spaceName, List<String> managers, List<String> participators, String creator) {
    this.name = name;
    this.spaceName = spaceName;
    this.managers = managers;
    this.participators = participators;
    this.creator = creator;
  }

  public boolean canEdit(Identity user) {
    return hasPermission(user, getManagers());
  }

  private boolean hasPermission(Identity user, List<String> permissions) {
    if (permissions.contains(user.getUserId())) {
      return true;
    } else {
      Set<MembershipEntry> memberships = new HashSet<>();
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
