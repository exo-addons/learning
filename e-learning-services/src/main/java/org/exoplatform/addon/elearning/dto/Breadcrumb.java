package org.exoplatform.addon.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Breadcrumb {
  private Long themeId;

  private String name;
  
  private boolean disabled;
}
