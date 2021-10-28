package org.exoplatform.addon.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step implements Serializable {
  private Long id;

  private String title;

  private String body;

  private Long media;

  private int order;

  private Long tutorialId;
}
