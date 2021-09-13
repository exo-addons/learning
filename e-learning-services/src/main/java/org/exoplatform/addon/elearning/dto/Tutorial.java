package org.exoplatform.addon.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutorial implements Serializable {
  private Long id;

  private String title;

  private String description;

  private String author;

  private Date createdDate;

  private List<Theme> themes = new ArrayList<>();

  private String status;

  private List<Step> steps = new ArrayList<>();

}
