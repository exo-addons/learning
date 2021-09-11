package org.exoplatform.addon.elearning.dao;

import org.apache.commons.lang3.StringUtils;
import org.exoplatform.addon.elearning.entity.ThemeEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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

  public List<ThemeEntity> findThemesBySpaceName(String spaceName, String query, int offset, int limit) {
    TypedQuery<ThemeEntity> q;
    if (StringUtils.isBlank(query)) {
      q = getEntityManager().createNamedQuery("ThemeEntity.findThemesBySpaceName", ThemeEntity.class);
      q.setParameter("spaceName", spaceName);
    } else {
      q = getEntityManager().createNamedQuery("ThemeEntity.searchSpaceThemesByThemeName", ThemeEntity.class);
      q.setParameter("spaceName", spaceName);
      q.setParameter("query", query);
    }
    if (limit > 0) {
      q.setFirstResult(offset).setMaxResults(limit);
    }
    try {
      return q.getResultList();
    } catch (NoResultException e) {
      return new ArrayList<>();
    }
  }

  public long countFoundThemesBySpaceName(String spaceName, String query) {
    TypedQuery<Long> q;
    if (StringUtils.isBlank(query)) {
      q = getEntityManager().createNamedQuery("ThemeEntity.countFoundThemesBySpaceName", Long.class);
      q.setParameter("spaceName", spaceName);
    } else {
      q = getEntityManager().createNamedQuery("ThemeEntity.countFoundSpaceThemesByThemeName", Long.class);
      q.setParameter("spaceName", spaceName);
      q.setParameter("query", query);
    }
    return q.getSingleResult();
  }
}
