package org.exoplatform.addon.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.exoplatform.addon.elearning.entity.ThemeEntity;
import org.exoplatform.addon.elearning.entity.TutorialEntity;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theme {
  private Long id;

  private String name;

  private String spaceName;

  private Set<String> managers;

  private Set<String> participators;

  private ThemeEntity parent;

  private List<ThemeEntity> children;

  private Long lastModifiedDate;

  private Set<TutorialEntity> tutorialEntities;

  public Theme(String name) {
    this.name = name;
  }

  public Theme(String name, String spaceName) {
    this.name = name;
    this.spaceName = spaceName;
  }


}
