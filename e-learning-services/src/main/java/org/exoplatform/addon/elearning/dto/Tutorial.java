package org.exoplatform.addon.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

  private Set<Theme> themes;

  public Tutorial(String title, String description, String author, String status, Set<Theme> themes) {
    this.title = title;
    this.description = description;
    this.author = author;
    this.status = status;
    this.themes = themes;
  }

}
