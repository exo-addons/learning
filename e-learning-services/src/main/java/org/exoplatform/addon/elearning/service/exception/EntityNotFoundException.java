package org.exoplatform.addon.elearning.service.exception;

public class EntityNotFoundException extends AbstractEntityException {
  public EntityNotFoundException(long id, Class<?> entityType) {
    super(id, entityType);
  }

  @Override
  public String getMessage() {
    if (getEntityId() != null && getEntityType() != null) {
      return getEntityType() + " does not exist with ID: " + getEntityId();
    }
    return super.getMessage();
  }
}
