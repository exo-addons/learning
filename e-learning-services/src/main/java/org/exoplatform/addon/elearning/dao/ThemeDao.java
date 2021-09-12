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

  public List<ThemeEntity> findThemesBySpaceName(String spaceName, boolean isRoot, String query, int offset, int limit) {
    StringBuilder stringBuilder;
    TypedQuery<ThemeEntity> q;
    if (StringUtils.isBlank(query)) {
      stringBuilder = new StringBuilder("SELECT t FROM ThemeEntity t where t.spaceName = :spaceName");
      if (isRoot) {
        stringBuilder.append(" AND t.parent is null");
      }
      stringBuilder.append(" order by t.lastModifiedDate desc");
      q = getEntityManager().createQuery(stringBuilder.toString(), ThemeEntity.class);
      q.setParameter("spaceName", spaceName);

    } else {
      stringBuilder = new StringBuilder("SELECT t FROM ThemeEntity t where t.spaceName = :spaceName");
      if (isRoot) {
        stringBuilder.append(" AND t.parent is null");
      }
      stringBuilder.append(" AND LOWER(t.name) LIKE LOWER(CONCAT('%', :query, '%')) order by t.lastModifiedDate desc");

      q = getEntityManager().createQuery(stringBuilder.toString(), ThemeEntity.class);
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

  public long countFoundThemesBySpaceName(String spaceName, boolean isRoot, String query) {
    StringBuilder stringBuilder;
    TypedQuery<Long> q;
    if (StringUtils.isBlank(query)) {
      stringBuilder = new StringBuilder("SELECT count (t) FROM ThemeEntity t where t.spaceName = :spaceName");
      if (isRoot) {
        stringBuilder.append(" AND t.parent is null");
      }
      q = getEntityManager().createQuery(stringBuilder.toString(), Long.class);
      q.setParameter("spaceName", spaceName);
    } else {
      stringBuilder = new StringBuilder("SELECT count (t) FROM ThemeEntity t where t.spaceName = :spaceName");
      if (isRoot) {
        stringBuilder.append(" AND t.parent is null");
      }
      stringBuilder.append(" AND LOWER(t.name) LIKE LOWER(CONCAT('%', :query, '%'))");
      q = getEntityManager().createQuery(stringBuilder.toString(), Long.class);
      q.setParameter("spaceName", spaceName);
      q.setParameter("query", query);
    }
    return q.getSingleResult();
  }

  public long countParentThemeChildren(long parentThemeId, String query) {
    TypedQuery<Long> q;
    if (StringUtils.isBlank(query)) {
      q = getEntityManager().createNamedQuery("ThemeEntity.countParentThemeChildren", Long.class);
      q.setParameter("id", parentThemeId);
    } else {
      q = getEntityManager().createNamedQuery("ThemeEntity.countSearchedParentThemeChildren", Long.class);
      q.setParameter("id", parentThemeId);
      q.setParameter("themeName", query);
    }
    return q.getSingleResult();

  }

  public List<ThemeEntity> retrieveChildThemes(long parentThemeId, String query, int offset, int limit) {
    TypedQuery<ThemeEntity> q;
    if (StringUtils.isBlank(query)) {
      q = getEntityManager().createNamedQuery("ThemeEntity.retrieveChildThemes", ThemeEntity.class);
      q.setParameter("id", parentThemeId);
    } else {
      q = getEntityManager().createNamedQuery("ThemeEntity.retrieveSearchedChildThemes", ThemeEntity.class);
      q.setParameter("id", parentThemeId);
      q.setParameter("themeName", query);
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
}
