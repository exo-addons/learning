package org.exoplatform.addon.elearning.service.configuration;

import org.exoplatform.addon.elearning.entities.TutorialEntity;
import org.exoplatform.addon.elearning.service.dto.Tutorial;
import org.exoplatform.addon.elearning.service.mapper.TutorialMapper;
import org.exoplatform.addon.elearning.storage.TutorialDao;
import org.exoplatform.services.rest.resource.ResourceContainer;

import java.util.Date;

public class TutorialService implements ResourceContainer {

  private TutorialDao tutorialDao;

  public TutorialService(TutorialDao tutorialDao) {
    this.tutorialDao = tutorialDao;
  }

  public Tutorial createTutorial(Tutorial tutorial) {
    tutorial.setCreatedDate(new Date(System.currentTimeMillis()));
    TutorialEntity tutorialEntity = tutorialDao.create(TutorialMapper.convertTutorialToEntity(tutorial));
    return TutorialMapper.convertTutorialToDTO(tutorialEntity);
  }

}
