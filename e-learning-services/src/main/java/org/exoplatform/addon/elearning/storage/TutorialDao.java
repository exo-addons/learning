package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.entities.TutorialEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class TutorialDao extends GenericDAOJPAImpl<TutorialEntity, Long> {

  public List<TutorialEntity> getAllTutosByTheme(Long id) {
    TypedQuery<TutorialEntity> q = getEntityManager().createNamedQuery("TutorialEntity.getAllTutosByTheme", TutorialEntity.class);
    q.setParameter("id", id);
    List<TutorialEntity> tutos = q.getResultList();
    return tutos;
  }

  public List<TutorialEntity> findTutosByName(String tutoTitle, Long id) {
    TypedQuery<TutorialEntity> q = getEntityManager().createNamedQuery("TutorialEntity.findTutosByName", TutorialEntity.class);
    q.setParameter("id", id);
    q.setParameter("tutoTitle", tutoTitle);
    try {
      return q.getResultList();
    } catch (NoResultException e) {
      return null;
    }
  }

}
