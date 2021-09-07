package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.dao.TutorialDao;
import org.exoplatform.addon.elearning.dto.Tutorial;
import org.exoplatform.addon.elearning.entity.TutorialEntity;
import org.exoplatform.addon.elearning.storage.mapper.TutorialMapper;

import java.util.List;

public class TutorialStorage {
  private TutorialDao tutorialDao;

  public TutorialStorage(TutorialDao tutorialDao) {
    this.tutorialDao = tutorialDao;
  }

  public Tutorial createTutorial(Tutorial tutorial) {
    TutorialEntity tutorialEntity = tutorialDao.create(TutorialMapper.convertTutorialToEntity(tutorial));
    return TutorialMapper.convertTutorialToDTO(tutorialEntity);
  }

  public Tutorial getTutorialById(Long id) {
    TutorialEntity tutorialEntity = tutorialDao.find(id);
    return TutorialMapper.convertTutorialToDTO(tutorialEntity);
  }

  public void deleteTutorial(Long id) {
    TutorialEntity tutorialEntity = tutorialDao.find(id);
    tutorialDao.delete(tutorialEntity);
  }

  public Tutorial updateTutorial(Tutorial tutorial) {
    TutorialEntity tutorialEntity = TutorialMapper.convertTutorialToEntity(tutorial);
    tutorialEntity = tutorialDao.update(tutorialEntity);
    return TutorialMapper.convertTutorialToDTO(tutorialEntity);
  }

  public List<Tutorial> getAllTutorials() {
    List<TutorialEntity> tutorialEntities = tutorialDao.findAll();
    return (List<Tutorial>) TutorialMapper.convertTutorialsToDTOs(tutorialEntities);
  }

  public List<Tutorial> getAllTutorialsByTheme(Long id) {
    List<TutorialEntity> tutorialEntities = tutorialDao.getAllTutorialsByTheme(id);
    return (List<Tutorial>) TutorialMapper.convertTutorialsToDTOs(tutorialEntities);
  }

  public List<Tutorial> findTutorialsByName(String tutorialTitle, Long id) {
    List<TutorialEntity> tutorialEntities = tutorialDao.findTutorialsByName(tutorialTitle, id);
    return (List<Tutorial>) TutorialMapper.convertTutorialsToDTOs(tutorialEntities);
  }
}
