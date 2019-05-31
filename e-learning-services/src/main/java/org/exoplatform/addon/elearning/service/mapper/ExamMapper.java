package org.exoplatform.addon.elearning.service.mapper;

import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.addon.elearning.entities.ExamEntity;
import org.exoplatform.addon.elearning.service.dto.ExamDTO;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExamMapper {
  private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  protected IdentityManager identityManager = null;

  public ExamMapper() {
    identityManager = CommonsUtils.getService(IdentityManager.class);
  }

  public ExamDTO examToExamDTO(ExamEntity exam){
    return new ExamDTO(exam);
  }

  public List<ExamDTO> examsToExamDTOs(List<ExamEntity> exams) {
    return exams.stream()
                .filter(Objects::nonNull)
                .map(this::examToExamDTO)
                .collect(Collectors.toList());
  }

  public ExamEntity examDTOToExam(ExamDTO examDTO) {
    try {
      if (examDTO == null) {
        return null;
      } else {
        String user = ConversationState.getCurrent().getIdentity().getUserId();
        ExamEntity exam = new ExamEntity();
        exam.setNameExam(examDTO.getNameExam());
        exam.setUserName(user);
        exam.setNbBidExam(examDTO.getNbBidExam());
        exam.setRewardExam(examDTO.getRewardExam());
        if (examDTO.getDateStartExam() != null) {
          exam.setDateStartExam(formatter.parse(examDTO.getDateStartExam()));
        }
        if (examDTO.getDateEndExam() != null) {
          exam.setDateEndExam(formatter.parse(examDTO.getDateEndExam()));
        }
        CourseEntity course=this.examFromLongId(examDTO.getIdCourse());
        exam.setUserName(user);
        exam.setCourse(course);
        return exam;
      }

    } catch (Exception pe) {
      pe.printStackTrace();
    }
    return null;
  }
  public CourseEntity examFromLongId(Long idCourse) {
    CourseEntity course=new CourseEntity();
    course.setIdCourse(idCourse);
    return course;
  }
}
