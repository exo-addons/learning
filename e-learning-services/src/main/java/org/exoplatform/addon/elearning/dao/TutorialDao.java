package org.exoplatform.addon.elearning.dao;

import org.exoplatform.addon.elearning.entity.TutorialEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class TutorialDao extends GenericDAOJPAImpl<TutorialEntity, Long> {

  public List<TutorialEntity> getAllTutorialsByTheme(Long id) {
    TypedQuery<TutorialEntity> q = getEntityManager().createNamedQuery("TutorialEntity.getAllTutorialsByTheme", TutorialEntity.class);
    q.setParameter("id", id);
    List<TutorialEntity> tutos = q.getResultList();
    return tutos;
  }

  public List<TutorialEntity> findTutorialsByName(String tutoTitle, Long id) {
    TypedQuery<TutorialEntity> q = getEntityManager().createNamedQuery("TutorialEntity.findTutorialsByName", TutorialEntity.class);
    q.setParameter("id", id);
    q.setParameter("tutoTitle", tutoTitle);
    try {
      return q.getResultList();
    } catch (NoResultException e) {
      return null;
    }
  }

}
