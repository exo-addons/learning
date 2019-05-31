package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.entities.LessonEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class LessonDao  extends GenericDAOJPAImpl<LessonEntity,Long> {
  public List<LessonEntity> findLessonsByIdCourse(Long id) throws PersistenceException{
    TypedQuery<LessonEntity>
        query = getEntityManager().createNamedQuery("ElearningLesson.findLessonById", LessonEntity.class);
    try {
      query.setParameter("id", id);
      return query.getResultList();
    } catch (NoResultException e) {
      return null;
    }
  }
}
