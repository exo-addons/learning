package org.exoplatform.addon.elearning.service.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutorial implements Serializable {
  private Long authorId;
  private Date createdDate;
  private Long id;
  private String status;
  private String description;
  private String title;
  private List<Step> steps;

  public Tutorial(String title, String description, Long authorId, String status) {
    this.title = title;
    this.description = description;
    this.authorId = authorId;
    this.status = status;
  }
}
