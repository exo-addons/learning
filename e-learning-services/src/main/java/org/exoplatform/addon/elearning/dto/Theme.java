package org.exoplatform.addon.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theme {
  private Long id;

  private String name;

  private String spaceName;

  public Theme(String name) {
    this.name = name;
  }

  public Theme(String name, String spaceName) {
    this.name = name;
    this.spaceName = spaceName;
  }


}
