package org.exoplatform.addon.elearning.dao;

import org.exoplatform.addon.elearning.entity.StepEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.TypedQuery;

public class StepDAO extends GenericDAOJPAImpl<StepEntity, Long> {
  public StepEntity findStepByOrder(Long tutorialId, int stepOrder) {
    TypedQuery<StepEntity> q = getEntityManager().createNamedQuery("StepEntity.findStepByOrder", StepEntity.class);
    q.setParameter("tutorialId", tutorialId);
    q.setParameter("stepOrder", stepOrder);
    try {
      return q.getSingleResult();
    } catch (Exception e) {
      return null;
    }
  }
}
