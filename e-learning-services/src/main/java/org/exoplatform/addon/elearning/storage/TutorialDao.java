package org.exoplatform.addon.elearning.storage;

import java.util.List;

import javax.persistence.TypedQuery;

import org.exoplatform.addon.elearning.entities.TutorialEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

public class TutorialDao extends GenericDAOJPAImpl<TutorialEntity, Long> {

  public List<TutorialEntity> getAllTutosByTheme(Long id) {
    TypedQuery<TutorialEntity> q = getEntityManager().createNamedQuery("TutorialEntity.getAllTutosByTheme", TutorialEntity.class);
    q.setParameter("id", id);
    List<TutorialEntity> tutos = q.getResultList();
    return tutos;
  }

}
