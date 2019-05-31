package org.exoplatform.addon.elearning.service.configuration;

import org.exoplatform.addon.elearning.entities.ExamRegistrationEntity;
import org.exoplatform.addon.elearning.service.dto.ExamRegistrationDTO;
import org.exoplatform.addon.elearning.service.mapper.ExamRegistrationMapper;
import org.exoplatform.addon.elearning.storage.ExamRegistrationDao;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExamRegistrationService {

  private              ExamRegistrationDao    examDao;
  private              ExamRegistrationMapper examMapper;
  private static final Log                    LOG = ExoLogger.getExoLogger(ExamRegistrationEntity.class);

  public ExamRegistrationService(){
    this.examDao= CommonsUtils.getService(ExamRegistrationDao.class);
    this.examMapper=CommonsUtils.getService(ExamRegistrationMapper.class);
  }

  @ExoTransactional
  public ExamRegistrationDTO addExam (ExamRegistrationDTO examRegistrationDTO) {

    ExamRegistrationEntity exam = null;

    try {

      exam = examDao.create(examMapper.examRegistrationDTOToExamRegistration(examRegistrationDTO));


    } catch (Exception e) {
      LOG.error("Error to create exam registration with title {}", examRegistrationDTO.getIdRegistration() , e);
    }

    return examMapper.examRegistrationToExamRegistrationDTO(exam);
  }
  public List<ExamRegistrationDTO> getAllExamsRegitered(String worker) {
    try {
      //--- load all registrations
      List<ExamRegistrationEntity> exams = examDao.findAll();
      LOG.info("contenu "+exams);
      if (exams != null) {
        return examMapper.examsRegistrationsToExamRegistartionDTOs(exams);
      }

    } catch (Exception e) {
      LOG.error("Error to find exams", e.getMessage());
    }
    return null;

  }

  public Long getNumberWorker(Long id){
    return examDao.getCountNumberEmploye(id);
  }
}
