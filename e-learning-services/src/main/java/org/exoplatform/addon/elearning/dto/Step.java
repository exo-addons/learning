package org.exoplatform.addon.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step implements Serializable {
  private Long id;

  private String title;

  private String content;

  private String mediaLink;

  private Long imageFileId;

  private int order;

  private Long tutorialId;

  private List<Attachment> attachments;
}
