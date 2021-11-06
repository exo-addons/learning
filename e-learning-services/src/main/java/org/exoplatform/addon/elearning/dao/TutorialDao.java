package org.exoplatform.addon.elearning.dao;

import org.exoplatform.addon.elearning.entity.TutorialEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class TutorialDao extends GenericDAOJPAImpl<TutorialEntity, Long> {

  public List<TutorialEntity> getTutorialsByTheme(Long themeId, int offset, int limit) {
    TypedQuery<TutorialEntity> q = getEntityManager().createNamedQuery("TutorialEntity.getTutorialsByTheme", TutorialEntity.class);
    q.setParameter("themeId", themeId);

    if (limit > 0) {
      q.setFirstResult(offset).setMaxResults(limit);
    }
    try {
      return q.getResultList();
    } catch (NoResultException e) {
      return new ArrayList<>();
    }
  }

  public List<TutorialEntity> findTutorialsByName(String tutorialTitle, Long id) {
    TypedQuery<TutorialEntity> q = getEntityManager().createNamedQuery("TutorialEntity.findTutorialsByName", TutorialEntity.class);
    q.setParameter("id", id);
    q.setParameter("tutorialTitle", tutorialTitle);
    try {
      return q.getResultList();
    } catch (NoResultException e) {
      return new ArrayList<>();
    }
  }

  public long countTutorialsByTheme(Long themeId) {
    TypedQuery<Long> q = getEntityManager().createNamedQuery("TutorialEntity.countTutorialsByTheme", Long.class);
    q.setParameter("themeId", themeId);
    return q.getSingleResult();
  }
}
