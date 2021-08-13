package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.entities.ThemeEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ThemeDao extends GenericDAOJPAImpl<ThemeEntity, Long> {

  public List<ThemeEntity> findAllThemesByName(String themeName) {
    TypedQuery<ThemeEntity> q = getEntityManager().createNamedQuery("ThemeEntity.findAllThemesByName", ThemeEntity.class);
    q.setParameter("themeName", themeName);
    try {
      return q.getResultList();
    } catch (NoResultException e) {
      return null;
    }
  }
}
