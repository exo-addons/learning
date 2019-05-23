package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.entities.ExamRegistrationEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

public class ExamRegistrationDao extends GenericDAOJPAImpl<ExamRegistrationEntity,Long> {
  public Long getCountNumberEmploye(Long id){
    Long count=((Long) getEntityManager().createNamedQuery("eregistration.getCountWorker").setParameter("id", id).getSingleResult()).longValue();
    return(count);

  }
}
