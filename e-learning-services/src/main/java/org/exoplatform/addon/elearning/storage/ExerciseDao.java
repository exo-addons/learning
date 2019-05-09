package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.entities.ExerciseEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ExerciseDao  extends GenericDAOJPAImpl<ExerciseEntity,Long> {
  public List<ExerciseEntity> findExercisesByCourseId(Long id, String user) throws PersistenceException {

    TypedQuery<ExerciseEntity>
        query =
        getEntityManager().createNamedQuery("ElearningExercise.findExercisesByCourseId", ExerciseEntity.class);
    try {
      query.setParameter("id",id);
      query.setParameter("user", user);
      return query.getResultList();

    } catch (NoResultException e) {
      return null;
    }
  }
}
