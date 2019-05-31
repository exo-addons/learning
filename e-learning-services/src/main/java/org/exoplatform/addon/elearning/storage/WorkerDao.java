package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.addon.elearning.entities.WorkerEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

public class WorkerDao  extends GenericDAOJPAImpl<WorkerEntity,Long> {
  public WorkerEntity findIdWorkerByname(String name) throws PersistenceException {

    TypedQuery<WorkerEntity>
        query = getEntityManager().createNamedQuery("ElearningWorker.getIdWorkerByName", WorkerEntity.class);
    query.setParameter("name", name);
    return query.getSingleResult();
    }
  }

