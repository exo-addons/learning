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
  public List<CourseEntity> getCompletedCourseByUser(CourseEntity.Status COMPLETED,String user){
    TypedQuery<CourseEntity> query = getEntityManager().createNamedQuery("ELearningCourse.getCompletedCourseByUser", CourseEntity.class);
    query.setParameter("COMPLETED",COMPLETED);
    query.setParameter("user", user);
    return query.getResultList();
  }
  public List<CourseEntity> getDrafetCourseByUser(CourseEntity.Status DRAFET,String user){
    TypedQuery<CourseEntity> query = getEntityManager().createNamedQuery("ELearningCourse.getDrafetCourseByUser", CourseEntity.class);
    query.setParameter("DRAFET",DRAFET);
    query.setParameter("user", user);
    return query.getResultList();
  }
  public List<CourseEntity> getPublishedCourseByUser(CourseEntity.Status PUBLISHED,String user){
    TypedQuery<CourseEntity> query = getEntityManager().createNamedQuery("ELearningCourse.getPublishedCourseByUser", CourseEntity.class);
    query.setParameter("PUBLISHED",PUBLISHED);
    query.setParameter("user", user);
    return query.getResultList();
  }

  public List<CourseEntity> getOtherPublishedCourse(CourseEntity.Status PUBLISHED,String user){
    TypedQuery<CourseEntity> query = getEntityManager().createNamedQuery("ELearningCourse.getOtherPublishedCourse", CourseEntity.class);
    query.setParameter("PUBLISHED",PUBLISHED);
    query.setParameter("user", user);
    return query.getResultList();
  }

  public int deleteCourseById(Long courseId) throws PersistenceException {
    return getEntityManager().createNamedQuery("ELearningCourse.deleteCourseById")
                             .setParameter("courseId", courseId)
                             .executeUpdate();

  }
  }

