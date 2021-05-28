package org.exoplatform.addon.elearning.service.configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.exoplatform.addon.elearning.entities.TutorialEntity;
import org.exoplatform.addon.elearning.service.dto.Tutorial;
import org.exoplatform.addon.elearning.service.mapper.TutorialMapper;
import org.exoplatform.addon.elearning.storage.TutorialDao;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.services.rest.resource.ResourceContainer;

public class TutorialService implements ResourceContainer {

  private TutorialDao tutorialDao;

  public TutorialService(TutorialDao tutorialDao) {
    this.tutorialDao = tutorialDao;
  }

  @ExoTransactional
  public Tutorial createTutorial(Tutorial tutorial) {
    TutorialEntity tutorialEntity = new TutorialEntity();
    try {
      tutorial.setCreatedDate(new Date(System.currentTimeMillis()));
      tutorialEntity = tutorialDao.create(TutorialMapper.convertTutorialToEntity(tutorial));
    } catch (Exception e) {
      Logger LOGGER = Logger.getLogger("LOG");
      LOGGER.info("Could not create Tutorial ERROR - " + e);
    }
    return TutorialMapper.convertTutorialToDTO(tutorialEntity);
  }

  @ExoTransactional
  public void deleteTutorial(Long id) {
    TutorialEntity tuto = new TutorialEntity();
    try {
      tuto = tutorialDao.find(id);

    } catch (Exception e) {
      Logger LOGGER = Logger.getLogger("LOG");
      LOGGER.info("Could not delete Tutorial ERROR - " + e);
    }
    tutorialDao.delete(tuto);
  }

  @ExoTransactional
  public Tutorial updateTutorial(Tutorial tuto) {
    TutorialEntity tutoEntity = new TutorialEntity();
    try {
      tutoEntity = tutorialDao.find(tuto.getId());
      tutoEntity.setTitle(tuto.getTitle());
      tutoEntity.setDescription(tuto.getDescription());
      tutoEntity.setAuthor(tuto.getAuthor());
      tutoEntity.setStatus(tuto.getStatus());
      tutoEntity.setThemeIds(tuto.getThemeIds());

    } catch (Exception e) {
      Logger LOGGER = Logger.getLogger("LOG");
      LOGGER.info("Could not update Tutorial ERROR - " + e);
    }
    return TutorialMapper.convertTutorialToDTO(tutoEntity);
  }

  public List<Tutorial> getAllTutos() {
    List<TutorialEntity> tutos = new ArrayList<TutorialEntity>();

    try {
      tutos = tutorialDao.findAll();

    } catch (Exception e) {
      Logger LOGGER = Logger.getLogger("LOG");
      LOGGER.info("Could not get all Tutorials ERROR - " + e);
    }
    return TutorialMapper.convertTutorialsToDTOs(tutos);
  }

  public Tutorial getTutoById(Long id) {
    TutorialEntity tuto = new TutorialEntity();
    try {
      tuto = tutorialDao.find(id);

    } catch (Exception e) {
      Logger LOGGER = Logger.getLogger("LOG");
      LOGGER.info("Could not get Tutorial with id " + id + " ERROR - " + e);
    }
    return TutorialMapper.convertTutorialToDTO(tuto);
  }

  public List<Tutorial> getAllTutosByTheme(Long id) {
    List<TutorialEntity> tutos = new ArrayList<TutorialEntity>();

    try {
      tutos = tutorialDao.getAllTutosByTheme(id);

    } catch (Exception e) {
      Logger LOGGER = Logger.getLogger("LOG");
      LOGGER.info("Could not get all Tutorials ERROR - " + e);
    }
    return TutorialMapper.convertTutorialsToDTOs(tutos);
  }

}
