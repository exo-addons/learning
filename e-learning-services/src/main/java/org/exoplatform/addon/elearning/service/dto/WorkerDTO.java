package org.exoplatform.addon.elearning.service.dto;

import org.exoplatform.addon.elearning.entities.WorkerEntity;

import java.io.Serializable;

public class WorkerDTO implements Serializable {
  private Long id;

  private String nameWorker;

  public WorkerDTO() {

  }

  public WorkerDTO(WorkerEntity work) {
    this.id = work.getIdWorker();
    this.nameWorker = work.getNameWorker();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNameWorker() {
    return nameWorker;
  }

  public void setNameWorker(String nameWorker) {
    this.nameWorker = nameWorker;
  }
}
