package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.entities.ExerciseEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ExerciseDao  extends GenericDAOJPAImpl<ExerciseEntity,Long> {
  public List<ExerciseEntity> findExercisesByCourseId(Long id, String user) throws PersistenceException {

    TypedQuery<ExerciseEntity> query = getEntityManager().createNamedQuery("ElearningExercise.findExercisesByCourseId", ExerciseEntity.class);
    try {
      query.setParameter("id", id);
      query.setParameter("user", user);
      return query.getResultList();
    } catch (NoResultException e) {
      return null;
    }
  }
  public List<ExerciseEntity> findExercisesByCourseExamId(Long idc,Long ide) throws PersistenceException {

    TypedQuery<ExerciseEntity> query = getEntityManager().createNamedQuery("ElearningExercise.findExercisesByCourseExamId", ExerciseEntity.class);
    try {
      query.setParameter("idc", idc);
      query.setParameter("ide", ide);
      return query.getResultList();
    } catch (NoResultException e) {
      return null;
    }
  }
  public List<ExerciseEntity> findExercisesByIdForOther(Long id, String user) throws PersistenceException {
    TypedQuery<ExerciseEntity> query = getEntityManager().createNamedQuery("ElearningExercise.findExercisesByIdForOther", ExerciseEntity.class);
    try {
      query.setParameter("id", id);
      query.setParameter("user", user);
      return query.getResultList();
    } catch (NoResultException e) {
      return null;
    }
  }

  public List<ExerciseEntity> findExercisesByIdExam(Long id) throws PersistenceException {
    TypedQuery<ExerciseEntity> query = getEntityManager().createNamedQuery("ElearningExercise.findExercisesByIdExam", ExerciseEntity.class);
    try {
      query.setParameter("id", id);
      return query.getResultList();
    } catch (NoResultException e) {
      return null;
    }
  }
}
