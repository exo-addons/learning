package org.exoplatform.addon.elearning.service.configuration;

import org.exoplatform.addon.elearning.entities.WorkerEntity;
import org.exoplatform.addon.elearning.service.dto.WorkerDTO;
import org.exoplatform.addon.elearning.service.mapper.WorkerMapper;
import org.exoplatform.addon.elearning.storage.WorkerDao;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationState;

import java.util.List;

public class WorkerService {
  private              WorkerDao    workerDao;

  private              WorkerMapper workerMapper;

  private static final Log          LOG = ExoLogger.getExoLogger(WorkerEntity.class);

  public WorkerService(WorkerDao workerDao, WorkerMapper workerMapper) {
    this.workerDao = CommonsUtils.getService(WorkerDao.class);
    ;
    this.workerMapper = CommonsUtils.getService(WorkerMapper.class);
  }

  public List<WorkerDTO> getAllWorkers() {
    try {
      List<WorkerEntity> workers = workerDao.findAll();
      if (workers != null) {
        return workerMapper.worksToWorkDTOs(workers);
      }

    } catch (Exception e) {
      LOG.error("Error to find Workers", e.getMessage());
    }
    return null;

  }

  @ExoTransactional
  public WorkerDTO addWorker(WorkerDTO workerDTO) {

    WorkerEntity worker = null;

    try {

      worker = workerDao.create(workerMapper.workDTOToWork(workerDTO));

    } catch (Exception e) {
      LOG.error("Error to create Worker with name {}", workerDTO.getNameWorker(), e);
    }

    return workerMapper.workToWorkDTO(worker);
  }

  // add a new user only one time for registration
  @ExoTransactional
  public WorkerDTO insertNewWorker(WorkerDTO workerDTO) {
    WorkerEntity worker = null;
    List<WorkerEntity> workerDaos = workerDao.findAll();
    String user = ConversationState.getCurrent().getIdentity().getUserId();
    boolean b = workerDaos.stream().anyMatch(work->work.getNameWorker().equals(user));
    LOG.info("/****************woker dto name" + user);
    LOG.info("/*******************boolean insert********************/ " + b);

    try {
      if(!b) {
        worker = workerDao.create(workerMapper.workDTOToWork(workerDTO));
      } else {
        WorkerEntity test = workerDaos.stream().filter(work->work.getNameWorker().equals(user)).findFirst().get();
        return  workerMapper.workToWorkDTO(test);
      }
    } catch (Exception e) {
      LOG.error("Error to create Worker", e);
    }
    return workerMapper.workToWorkDTO(worker);

  }

  public WorkerDTO getIdWorkerByName(String name) {
    try {
      WorkerEntity worker = workerDao.findIdWorkerByname(name);
      if (worker != null) {
        return workerMapper.workToWorkDTO(worker);
      }

    } catch (Exception e) {
      LOG.error("Error to find Worker", e.getMessage());
    }
    return null;

  }

}

