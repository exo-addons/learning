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
  public List<CourseEntity> getArchivedCourseByUser(CourseEntity.Status ARCHIVED,String user){
    TypedQuery<CourseEntity> query = getEntityManager().createNamedQuery("ELearningCourse.getArchivedCourseByUser", CourseEntity.class);
    query.setParameter("ARCHIVED",ARCHIVED);
    query.setParameter("user", user);
    return query.getResultList();
  }
  public List<CourseEntity> getDraftCourseByUser(CourseEntity.Status DRAFT,String user){
    TypedQuery<CourseEntity> query = getEntityManager().createNamedQuery("ELearningCourse.getDraftCourseByUser", CourseEntity.class);
    query.setParameter("DRAFT",DRAFT);
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


  }

