package org.exoplatform.addon.elearning.service;

import org.exoplatform.addon.elearning.dto.Step;
import org.exoplatform.addon.elearning.dto.Tutorial;
import org.exoplatform.addon.elearning.storage.StepStorage;
import org.exoplatform.addon.elearning.storage.TutorialStorage;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.services.rest.resource.ResourceContainer;

import java.util.List;

public class TutorialService implements ResourceContainer {

  private TutorialStorage tutorialStorage;

  private StepStorage stepStorage;

  public TutorialService(TutorialStorage tutorialStorage, StepStorage stepStorage) {
    this.tutorialStorage = tutorialStorage;
    this.stepStorage = stepStorage;
  }

  @ExoTransactional
  public Tutorial createTutorial(Tutorial tutorial) throws IllegalAccessException {
    if (tutorial == null) {
      throw new IllegalAccessException("Tutorial is mandatory");
    }
    return tutorialStorage.createTutorial(tutorial);
  }

  @ExoTransactional
  public void deleteTutorial(Long id) {
    tutorialStorage.deleteTutorial(id);
  }

  @ExoTransactional
  public Tutorial updateTutorial(Tutorial tutorial) {
    return tutorialStorage.updateTutorial(tutorial);
  }

  public List<Tutorial> getAllTutorials(int offset, int limit) {
    return tutorialStorage.getAllTutorials();
  }

  public Tutorial getTutorialById(Long id) {
    return tutorialStorage.getTutorialById(id);
  }

  public List<Tutorial> getAllTutorialsByTheme(Long id, int offset, int limit) {
    return tutorialStorage.getAllTutorialsByTheme(id);
  }

  public List<Tutorial> findTutorialsByName(String tutorialTitle, Long id, int offset, int limit) {
    return tutorialStorage.findTutorialsByName(tutorialTitle, id);
  }

  @ExoTransactional
  public Step addTutorialStep(Step step, Long tutorialId) {
    Tutorial tutorial = tutorialStorage.getTutorialById(tutorialId);
    return stepStorage.addStep(step, tutorial);
  }

  @ExoTransactional
  public Step updateTutorialStep(Step step) {
    return stepStorage.updateStep(step);
  }

  @ExoTransactional
  public void deleteTutorialStep(Long id) {
    stepStorage.deleteStepById(id);
  }

}
