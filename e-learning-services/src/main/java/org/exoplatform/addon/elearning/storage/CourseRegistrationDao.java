package org.exoplatform.addon.elearning.storage;
import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.addon.elearning.entities.CourseRegistrationEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class CourseRegistrationDao extends GenericDAOJPAImpl<CourseRegistrationEntity,Long>  {
  public List<CourseRegistrationEntity> findRegitrationByIdWorker(String name) throws PersistenceException {

    TypedQuery<CourseRegistrationEntity> query = getEntityManager().createNamedQuery("cregistration.findRegitrationByIdWorker", CourseRegistrationEntity.class);
    query.setParameter("name",name);
    return query.getResultList();

  }
}
