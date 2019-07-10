package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class CourseDao extends GenericDAOJPAImpl<CourseEntity,Long> {
  public CourseEntity findCourseByName(String courseName) throws PersistenceException {

    TypedQuery<CourseEntity> query = getEntityManager().createNamedQuery("ELearningCourse.findCourseByName",CourseEntity.class);
    query.setParameter("courseName","%"+courseName+"%");
    try {
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }

  }
  public List<CourseEntity> getCourseByUserAndStatus(String status,String user){
    TypedQuery<CourseEntity> query = getEntityManager().createNamedQuery("ELearningCourse.getCourseByUserAndStatus", CourseEntity.class);
    query.setParameter("status",status);
    query.setParameter("user", user);
    return query.getResultList();
  }

  public List<CourseEntity> getOtherPublishedCourse(String user){
    TypedQuery<CourseEntity> query = getEntityManager().createNamedQuery("ELearningCourse.getOtherPublishedCourse", CourseEntity.class);
    query.setParameter("status","PUBLISHED");
    query.setParameter("user", user);
    return query.getResultList();
  }


  }

