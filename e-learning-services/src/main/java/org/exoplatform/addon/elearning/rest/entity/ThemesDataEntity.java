package org.exoplatform.addon.elearning.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.exoplatform.addon.elearning.dto.Theme;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemesDataEntity implements Serializable {
  private long count;
  private boolean canUpdate;
  private SpaceDataEntity space;
  private List<Theme> themeList = new ArrayList<>();
}
