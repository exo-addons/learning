package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.dao.StepDAO;
import org.exoplatform.addon.elearning.dto.Step;
import org.exoplatform.addon.elearning.dto.Tutorial;
import org.exoplatform.addon.elearning.entity.StepEntity;
import org.exoplatform.addon.elearning.storage.mapper.StepMapper;
import org.exoplatform.addon.elearning.storage.mapper.TutorialMapper;

public class StepStorage {
  private StepDAO stepDAO;

  public StepStorage(StepDAO stepDAO) {
    this.stepDAO = stepDAO;
  }

  public Step addStep(Step step, Tutorial tutorial) {
    StepEntity stepEntity = StepMapper.convertStepToEntity(step);
    stepEntity.setTutorialEntity(TutorialMapper.convertTutorialToEntity(tutorial));
    stepEntity = stepDAO.create(stepEntity);
    return StepMapper.convertStepToDTO(stepEntity);
  }

  public Step getStepById(Long id) {
    StepEntity stepEntity = stepDAO.find(id);
    return StepMapper.convertStepToDTO(stepEntity);
  }

  public Step updateStep(Step step) {
    StepEntity stepEntity = stepDAO.update(StepMapper.convertStepToEntity(step));
    return StepMapper.convertStepToDTO(stepEntity);
  }

  public void deleteStepById(Long id) {
    StepEntity stepEntity = stepDAO.find(id);
    stepDAO.delete(stepEntity);
  }
}
