package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.dao.AttachmentDAO;
import org.exoplatform.addon.elearning.dao.StepDAO;
import org.exoplatform.addon.elearning.dao.ThemeDao;
import org.exoplatform.addon.elearning.dao.TutorialDao;
import org.exoplatform.addon.elearning.dto.Step;
import org.exoplatform.addon.elearning.entity.StepEntity;
import org.exoplatform.addon.elearning.entity.TutorialEntity;
import org.exoplatform.addon.elearning.storage.mapper.StepMapper;
import org.exoplatform.commons.api.persistence.ExoTransactional;

import java.util.List;

public class StepStorage {
  private StepDAO stepDAO;
  private ThemeDao themeDao;
  private TutorialDao tutorialDao;
  private AttachmentDAO attachmentDAO;

  public StepStorage(StepDAO stepDAO, ThemeDao themeDao, TutorialDao tutorialDao, AttachmentDAO attachmentDAO) {
    this.stepDAO = stepDAO;
    this.themeDao = themeDao;
    this.tutorialDao = tutorialDao;
    this.attachmentDAO = attachmentDAO;
  }

  @ExoTransactional
  public Step addStep(Step step) {
    StepEntity stepEntity = stepDAO.create(StepMapper.convertStepToEntity(step, themeDao, tutorialDao, attachmentDAO));

    TutorialEntity tutorialEntity = tutorialDao.find(stepEntity.getTutorial().getId());
    tutorialEntity.setLastModifiedDate(System.currentTimeMillis());
    tutorialDao.update(tutorialEntity);
    return StepMapper.convertStepToDTO(stepEntity);
  }

  public Step getStepById(Long id) {
    StepEntity stepEntity = stepDAO.find(id);
    return StepMapper.convertStepToDTO(stepEntity);
  }

  @ExoTransactional
  public Step updateStep(Step step) {
    StepEntity stepEntity = stepDAO.update(StepMapper.convertStepToEntity(step, themeDao, tutorialDao, attachmentDAO));

    TutorialEntity tutorialEntity = tutorialDao.find(stepEntity.getTutorial().getId());
    tutorialEntity.setLastModifiedDate(System.currentTimeMillis());
    tutorialDao.update(tutorialEntity);
    return StepMapper.convertStepToDTO(stepEntity);
  }

  @ExoTransactional
  public Step deleteStepById(Long id) {
    StepEntity stepEntity = stepDAO.find(id);
    stepDAO.delete(stepEntity);
    return StepMapper.convertStepToDTO(stepEntity);
  }

  public Step findStepByOrder(Long tutorialId, int stepOrder) {
    StepEntity stepEntity = stepDAO.findStepByOrder(tutorialId, stepOrder);
    return StepMapper.convertStepToDTO(stepEntity);
  }

  public List<Step> getTutorialSteps(Long tutorialId) {
    List<StepEntity> stepEntities = stepDAO.findAllTutorialSteps(tutorialId);
    return StepMapper.convertStepsToDTOs(stepEntities);
  }

  @ExoTransactional
  public void updatePostStepsOder(Long tutorialId, int order) {
    List<StepEntity> postStepEntities = stepDAO.getPostStepsToCurrentOrder(tutorialId, order);
    for (StepEntity postStepEntity : postStepEntities) {
      int newOrder = postStepEntity.getOrder() - 1;
      postStepEntity.setOrder(newOrder);
      stepDAO.update(postStepEntity);
    }
  }
}
