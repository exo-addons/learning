package org.exoplatform.addon.elearning.service.mapper;

import org.exoplatform.addon.elearning.entities.WorkerEntity;
import org.exoplatform.addon.elearning.rest.ExamRestService;
import org.exoplatform.addon.elearning.service.configuration.WorkerService;
import org.exoplatform.addon.elearning.service.dto.WorkerDTO;
import org.exoplatform.addon.elearning.storage.WorkerDao;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WorkerMapper {
  protected        IdentityManager identityManager = null;
  private static Log             LOG             = ExoLogger.getLogger(ExamRestService.class);


  private WorkerService workerService;
  private WorkerDao workerDao;

  public WorkerMapper() {
    this.identityManager = CommonsUtils.getService(IdentityManager.class);
  }

  public WorkerDTO workToWorkDTO(WorkerEntity work) {
    return new WorkerDTO(work);
  }

  public List<WorkerDTO> worksToWorkDTOs(List<WorkerEntity> works) {
    return works.stream()
                .filter(Objects::nonNull)
                .map(this::workToWorkDTO)
                .collect(Collectors.toList());
  }

  public WorkerEntity workDTOToWork(WorkerDTO workerDTO) {

    try {
      if (workerDTO == null) {
        return null;
      } else {
        String user = ConversationState.getCurrent().getIdentity().getUserId();
        WorkerEntity worker = new WorkerEntity();
        worker.setNameWorker(user);
        return worker;
      }

    } catch (Exception pe) {
      pe.printStackTrace();
    }
    return null;
  }

}
