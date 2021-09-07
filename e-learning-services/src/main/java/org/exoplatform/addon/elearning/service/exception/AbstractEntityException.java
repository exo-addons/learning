package org.exoplatform.addon.elearning.service.exception;

public class AbstractEntityException extends Exception {

  private Long entityId;

  private Class<?> entityType;

  public AbstractEntityException(Long entityId, Class<?> entityType) {
    this.entityId = entityId;
    this.entityType = entityType;
  }

  @Override
  public String getMessage() {
    if (entityId != null && entityType != null) {
      return "Exception on " + getEntityType() + " with ID: " + entityId;
    }
    return super.getMessage();
  }

  public Long getEntityId() {
    return entityId;
  }

  public String getEntityType() {
    return entityType != null ? entityType.getSimpleName() : null;
  }
}
