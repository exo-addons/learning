package org.exoplatform.addon.elearning.service.configuration;

import org.exoplatform.addon.elearning.entities.TutorialEntity;
import org.exoplatform.addon.elearning.service.dto.Tutorial;
import org.exoplatform.addon.elearning.service.mapper.TutorialMapper;
import org.exoplatform.addon.elearning.storage.TutorialDao;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.services.rest.resource.ResourceContainer;

import java.util.logging.Logger;

import java.util.Date;

public class TutorialService implements ResourceContainer {

  private TutorialDao tutorialDao;

  public TutorialService(TutorialDao tutorialDao) {
    this.tutorialDao = tutorialDao;
  }

  @ExoTransactional
  public Tutorial createTutorial(Tutorial tutorial) {
    TutorialEntity tutorialEntity = null;
    try {
    tutorial.setCreatedDate(new Date(System.currentTimeMillis()));
    tutorialEntity = tutorialDao.create(TutorialMapper.convertTutorialToEntity(tutorial));
    } catch (Exception e) {
      Logger LOGGER = Logger.getLogger("LOG");
      LOGGER.info("Could not create Tutorial ERROR - "+e); 
    }
    return TutorialMapper.convertTutorialToDTO(tutorialEntity);
  }

}
