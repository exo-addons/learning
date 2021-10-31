package org.exoplatform.addon.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attachment implements Serializable {
  private Long id;

  private String fullTitle;
  
  private Date createdDate;
  
  private Long attachmentFileID;
  
  private Long stepId;
}
