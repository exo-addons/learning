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

  private Long lastModifiedDate;

  private List<Long> themeIds = new ArrayList<>();

  private String status;

  private List<Long> stepsIds = new ArrayList<>();

}
