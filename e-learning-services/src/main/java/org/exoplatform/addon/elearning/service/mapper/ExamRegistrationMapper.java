package org.exoplatform.addon.elearning.service.mapper;

import org.exoplatform.addon.elearning.entities.ExamEntity;
import org.exoplatform.addon.elearning.entities.ExamRegistrationEntity;
import org.exoplatform.addon.elearning.entities.WorkerEntity;
import org.exoplatform.addon.elearning.service.dto.ExamRegistrationDTO;
import org.exoplatform.services.security.ConversationState;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExamRegistrationMapper {
  private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  public ExamRegistrationMapper() {
  }
  public ExamRegistrationDTO examRegistrationToExamRegistrationDTO(ExamRegistrationEntity course){
    return new ExamRegistrationDTO(course);
  }
  public List<ExamRegistrationDTO> examsRegistrationsToExamRegistartionDTOs(List<ExamRegistrationEntity> exams){
    return exams.stream()
                  .filter(Objects::nonNull)
                  .map(this::examRegistrationToExamRegistrationDTO)
                  .collect(Collectors.toList());
  }
  public ExamRegistrationEntity examRegistrationDTOToExamRegistration(ExamRegistrationDTO examRegistrationDTO){
    try {
      if(examRegistrationDTO==null){
        return null;
      }else{
        String user = ConversationState.getCurrent().getIdentity().getUserId();
        ExamRegistrationEntity examRegistration=new ExamRegistrationEntity();
        examRegistration.setRegisteredExam(new Date());
        WorkerEntity worker=this.workerFromLongId(examRegistrationDTO.getIdWorker());
        examRegistration.setWorker(worker);
        ExamEntity exam=this.examFromLongId(examRegistrationDTO.getIdExam());
        examRegistration.setExam(exam);
        examRegistration.setMarkExam(examRegistrationDTO.getMark());
        examRegistration.setUserName(user);
        return examRegistration;

      }

    }catch(Exception pe){
      pe.printStackTrace();
    }
    return null;
  }
  public WorkerEntity workerFromLongId(Long idWorker) {
    WorkerEntity worker=new WorkerEntity();
    worker.setIdWorker(idWorker);
    return worker;
  }
  public ExamEntity examFromLongId(Long idExam) {
    ExamEntity exam=new ExamEntity();
    exam.setIdExam(idExam);
    return exam;
  }

}
