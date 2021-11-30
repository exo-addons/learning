package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.dao.StepDAO;
import org.exoplatform.addon.elearning.dao.ThemeDao;
import org.exoplatform.addon.elearning.dao.TutorialDao;
import org.exoplatform.addon.elearning.dto.Tutorial;
import org.exoplatform.addon.elearning.entity.ThemeEntity;
import org.exoplatform.addon.elearning.entity.TutorialEntity;
import org.exoplatform.addon.elearning.storage.mapper.TutorialMapper;
import org.exoplatform.commons.api.persistence.ExoTransactional;

import java.util.List;

public class TutorialStorage {
  private TutorialDao tutorialDao;
  private ThemeDao themeDao;
  private StepDAO stepDAO;

  public TutorialStorage(TutorialDao tutorialDao, ThemeDao themeDao, StepDAO stepDAO) {
    this.tutorialDao = tutorialDao;
    this.themeDao = themeDao;
    this.stepDAO = stepDAO;
  }

  @ExoTransactional
  public Tutorial createTutorial(Tutorial tutorial, List<Long> themeIds) {
    TutorialEntity tutorialEntity = tutorialDao.create(TutorialMapper.convertTutorialToEntity(tutorial, themeDao, stepDAO));

    for (Long themeId : themeIds) {
      ThemeEntity themeEntity = themeDao.find(themeId);
      tutorialEntity.addThemeEntity(themeEntity);

      themeEntity.setLastModifiedDate(System.currentTimeMillis());
      themeDao.update(themeEntity);
    }
    tutorialEntity = tutorialDao.update(tutorialEntity);
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
    TutorialEntity tutorialEntity = TutorialMapper.convertTutorialToEntity(tutorial, themeDao, stepDAO);
    tutorialEntity = tutorialDao.update(tutorialEntity);
    List<ThemeEntity> themeEntities = tutorialEntity.getThemeEntities();
    for (ThemeEntity themeEntity : themeEntities) {
      themeEntity.setLastModifiedDate(System.currentTimeMillis());
      themeDao.update(themeEntity);
    }
    return TutorialMapper.convertTutorialToDTO(tutorialEntity);
  }

  public List<Tutorial> getAllTutorials() {
    List<TutorialEntity> tutorialEntities = tutorialDao.findAll();
    return TutorialMapper.convertTutorialsToDTOs(tutorialEntities);
  }

  public List<Tutorial> getTutorialsByTheme(Long themeId, int offset, int limit) {
    List<TutorialEntity> tutorialEntities = tutorialDao.getTutorialsByTheme(themeId, offset, limit);
    return TutorialMapper.convertTutorialsToDTOs(tutorialEntities);
  }

  public List<Tutorial> findTutorialsByName(String tutorialTitle, Long id) {
    List<TutorialEntity> tutorialEntities = tutorialDao.findTutorialsByName(tutorialTitle, id);
    return TutorialMapper.convertTutorialsToDTOs(tutorialEntities);
  }

  public long countTutorialsByTheme(Long themeId) {
    return tutorialDao.countTutorialsByTheme(themeId);
  }
}
