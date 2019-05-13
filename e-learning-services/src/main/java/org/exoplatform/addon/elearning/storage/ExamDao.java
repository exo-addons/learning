package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.addon.elearning.entities.ExamEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.TypedQuery;
import java.util.List;

public class ExamDao extends GenericDAOJPAImpl<ExamEntity,Long> {
  public List<ExamEntity> getExamByUser(String user){
    TypedQuery<ExamEntity> query = getEntityManager().createNamedQuery("ElearningExam.findExamByUser", ExamEntity.class);
    query.setParameter("user", user);
    return query.getResultList();
  }
}
