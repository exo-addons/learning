package org.exoplatform.addon.elearning.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceDataEntity implements Serializable {
  private String id;
  private String displayName;
  private String prettyName;
  private String avatarUrl;
  private String url;
  private String groupId;

}
