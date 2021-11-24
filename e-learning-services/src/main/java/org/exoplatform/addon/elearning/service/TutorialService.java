package org.exoplatform.addon.elearning.service;

import org.exoplatform.addon.elearning.dto.Step;
import org.exoplatform.addon.elearning.dto.Tutorial;
import org.exoplatform.addon.elearning.storage.StepStorage;
import org.exoplatform.addon.elearning.storage.TutorialStorage;
import org.exoplatform.services.rest.resource.ResourceContainer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TutorialService implements ResourceContainer {

  private TutorialStorage tutorialStorage;

  private StepStorage stepStorage;

  public TutorialService(TutorialStorage tutorialStorage, StepStorage stepStorage) {
    this.tutorialStorage = tutorialStorage;
    this.stepStorage = stepStorage;
  }

  public Tutorial createTutorial(Tutorial tutorial) throws IllegalAccessException {
    if (tutorial == null) {
      throw new IllegalAccessException("Tutorial is mandatory");
    }
    List<Long> themeIds = new ArrayList<>(tutorial.getThemeIds());
    if (themeIds.isEmpty()) {
      throw new IllegalAccessException("At least one theme is mandatory");
    }
    tutorial.getThemeIds().clear();
    tutorial.setCreatedDate(new Date(System.currentTimeMillis()));

    return tutorialStorage.createTutorial(tutorial, themeIds);
  }

  public void deleteTutorial(Long id) {
    tutorialStorage.deleteTutorial(id);
  }

  public Tutorial updateTutorial(Tutorial tutorial) {
    tutorial.setLastModifiedDate(System.currentTimeMillis());
    return tutorialStorage.updateTutorial(tutorial);
  }

  public List<Tutorial> getAllTutorials(int offset, int limit) {
    return tutorialStorage.getAllTutorials();
  }

  public Tutorial getTutorialById(Long id) {
    return tutorialStorage.getTutorialById(id);
  }

  public List<Tutorial> getTutorialsByTheme(Long themeId, int offset, int limit) {
    return tutorialStorage.getTutorialsByTheme(themeId, offset, limit);
  }

  public List<Tutorial> findTutorialsByName(String tutorialTitle, Long id, int offset, int limit) {
    return tutorialStorage.findTutorialsByName(tutorialTitle, id);
  }

  public Step addTutorialStep(Step step, Long tutorialId) {
    step.setTutorialId(tutorialId);
    return stepStorage.addStep(step);
  }

  public Step updateTutorialStep(Step step) {
    return stepStorage.updateStep(step);
  }

  public void deleteTutorialStep(Long id) {
    stepStorage.deleteStepById(id);
  }

  public long countTutorialsByTheme(Long themeId) {
    return tutorialStorage.countTutorialsByTheme(themeId);
  }

  public Step getTutorialStepByOrder(Long tutorialId, int stepOrder) {
    return stepStorage.findStepByOrder(tutorialId, stepOrder);
  }
}
