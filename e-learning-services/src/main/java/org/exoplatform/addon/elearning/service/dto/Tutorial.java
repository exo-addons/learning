package org.exoplatform.addon.elearning.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutorial implements Serializable {
  private String author;

  private Date createdDate;

  private Long id;

  private String status;

  private String description;

  private String title;

  private List<Step> steps;

  private List<Long> themeIds;

  public Tutorial(String title, String description, String author, String status, List<Long> themeIds) {
    this.title = title;
    this.description = description;
    this.author = author;
    this.status = status;
    this.themeIds = themeIds;
  }

}
