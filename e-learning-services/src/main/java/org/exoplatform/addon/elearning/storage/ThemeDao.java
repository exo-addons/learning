package org.exoplatform.addon.elearning.storage;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.exoplatform.addon.elearning.entities.ThemeEntity;
import org.exoplatform.addon.elearning.entities.TutorialEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

public class ThemeDao extends GenericDAOJPAImpl<ThemeEntity, Long> {

  public List<String> getAllThemeNames() {
    TypedQuery<ThemeEntity> q = getEntityManager().createNamedQuery("ThemeEntity.getAllNames", ThemeEntity.class);
    List<ThemeEntity> themes = q.getResultList();
    List<String> names = new ArrayList<String>();
    for (ThemeEntity t : themes) {
      names.add(t.getName());
    }
    return names;
  }
  
  public List<ThemeEntity> findAllThemesByName (String themeName) {
    TypedQuery<ThemeEntity> q = getEntityManager().createNamedQuery("ThemeEntity.findAllThemesByName", ThemeEntity.class);
    q.setParameter("themeName", themeName);
    try {
      return q.getResultList();
    } catch (NoResultException e) {
      return null;
    }
  }
}
