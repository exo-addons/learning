package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.dao.StepDAO;
import org.exoplatform.addon.elearning.dao.ThemeDao;
import org.exoplatform.addon.elearning.dao.TutorialDao;
import org.exoplatform.addon.elearning.dto.Step;
import org.exoplatform.addon.elearning.dto.Tutorial;
import org.exoplatform.addon.elearning.entity.StepEntity;
import org.exoplatform.addon.elearning.storage.mapper.StepMapper;
import org.exoplatform.addon.elearning.storage.mapper.TutorialMapper;

public class StepStorage {
  private StepDAO stepDAO;
  private ThemeDao themeDao;
  private TutorialDao tutorialDao;

  public StepStorage(StepDAO stepDAO, ThemeDao themeDao, TutorialDao tutorialDao) {
    this.stepDAO = stepDAO;
    this.themeDao = themeDao;
    this.tutorialDao = tutorialDao;
  }

  public Step addStep(Step step, Tutorial tutorial) {
    StepEntity stepEntity = StepMapper.convertStepToEntity(step, themeDao, tutorialDao);
    stepEntity.setTutorialEntity(TutorialMapper.convertTutorialToEntity(tutorial, themeDao));
    stepEntity = stepDAO.create(stepEntity);
    return StepMapper.convertStepToDTO(stepEntity);
  }

  public Step getStepById(Long id) {
    StepEntity stepEntity = stepDAO.find(id);
    return StepMapper.convertStepToDTO(stepEntity);
  }

  public Step updateStep(Step step) {
    StepEntity stepEntity = stepDAO.update(StepMapper.convertStepToEntity(step, themeDao, tutorialDao));
    return StepMapper.convertStepToDTO(stepEntity);
  }

  public void deleteStepById(Long id) {
    StepEntity stepEntity = stepDAO.find(id);
    stepDAO.delete(stepEntity);
  }
}
