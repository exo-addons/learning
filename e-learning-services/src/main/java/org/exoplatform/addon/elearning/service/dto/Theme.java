package org.exoplatform.addon.elearning.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theme {
  private Long   id;

  private String name;

  public Theme(String name) {
    this.name = name;
  }

}
