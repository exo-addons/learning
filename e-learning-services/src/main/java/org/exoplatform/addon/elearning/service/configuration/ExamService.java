package org.exoplatform.addon.elearning.service.configuration;

import org.exoplatform.addon.elearning.entities.ExamEntity;
import org.exoplatform.addon.elearning.service.dto.ExamDTO;
import org.exoplatform.addon.elearning.service.mapper.ExamMapper;
import org.exoplatform.addon.elearning.storage.ExamDao;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import java.util.List;

public class ExamService {
  private              ExamDao    examDao;
  private              ExamMapper examMapper;
  private static final Log        LOG = ExoLogger.getExoLogger(ExamEntity.class);

  public ExamService(ExamDao examDao, ExamMapper examMapper) {
    this.examDao =  CommonsUtils.getService(ExamDao.class);
    this.examMapper = CommonsUtils.getService(ExamMapper.class);
  }

  public List<ExamDTO> getAllExams() {
    try {
      List<ExamEntity> exams = examDao.findAll();
      if (exams != null) {
        return examMapper.examsToExamDTOs(exams);
      }

    } catch (Exception e) {
      LOG.error("Error to find Exams", e.getMessage());
    }
    return null;

  }

  @ExoTransactional
  public ExamDTO addExam(ExamDTO examDTO) {

    ExamEntity exam = null;

    try {

      exam = examDao.create(examMapper.examDTOToExam(examDTO));

    } catch (Exception e) {
      LOG.error("Error to create exam with title {}", examDTO.getNameExam(), e);
    }

    return examMapper.examToExamDTO(exam);
  }

  public List<ExamDTO> getExamById(Long id){
    try {
      List<ExamEntity> exams = examDao.getExamById(id);
      if (exams != null) {
        return examMapper.examsToExamDTOs(exams);
      }

    } catch (Exception e) {
      LOG.error("Error to find published exam", e.getMessage());
    }
    return null;

  }

  public List<ExamDTO> getExamByUserName(String user){
    try {
      List<ExamEntity> exams = examDao.getExamByUserName(user);
      if (exams != null) {
        return examMapper.examsToExamDTOs(exams);
      }

    } catch (Exception e) {
      LOG.error("Error to find published exam by user", e.getMessage());
    }
    return null;

  }

}
