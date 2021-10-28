package org.exoplatform.addon.elearning.storage;

import org.exoplatform.addon.elearning.dao.ThemeDao;
import org.exoplatform.addon.elearning.dao.TutorialDao;
import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.dto.Tutorial;
import org.exoplatform.addon.elearning.entity.ThemeEntity;
import org.exoplatform.addon.elearning.entity.TutorialEntity;
import org.exoplatform.addon.elearning.storage.mapper.ThemeMapper;
import org.exoplatform.addon.elearning.storage.mapper.TutorialMapper;
import org.exoplatform.commons.api.persistence.ExoTransactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TutorialStorage {
  private TutorialDao tutorialDao;
  private ThemeDao themeDao;

  public TutorialStorage(TutorialDao tutorialDao, ThemeDao themeDao) {
    this.tutorialDao = tutorialDao;
    this.themeDao = themeDao;
  }

  @ExoTransactional
  public Tutorial createTutorial(Tutorial tutorial, List<Long> themeIds) {
    TutorialEntity tutorialEntity = tutorialDao.create(TutorialMapper.convertTutorialToEntity(tutorial, themeDao));
    List<Theme> themes = new ArrayList<>();
    
    for (Long themeId : themeIds) {
      ThemeEntity themeEntity = themeDao.find(themeId);
      tutorialEntity.addThemeEntity(themeEntity);
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
    TutorialEntity tutorialEntity = TutorialMapper.convertTutorialToEntity(tutorial, themeDao);
    tutorialEntity = tutorialDao.update(tutorialEntity);
    return TutorialMapper.convertTutorialToDTO(tutorialEntity);
  }

  public List<Tutorial> getAllTutorials() {
    List<TutorialEntity> tutorialEntities = tutorialDao.findAll();
    return TutorialMapper.convertTutorialsToDTOs(new HashSet<>(tutorialEntities));
  }

  public List<Tutorial> getTutorialsByTheme(Long themeId, int offset, int limit) {
    List<TutorialEntity> tutorialEntities = tutorialDao.getTutorialsByTheme(themeId, offset, limit);
    return TutorialMapper.convertTutorialsToDTOs(new HashSet<>(tutorialEntities));
  }

  public List<Tutorial> findTutorialsByName(String tutorialTitle, Long id) {
    List<TutorialEntity> tutorialEntities = tutorialDao.findTutorialsByName(tutorialTitle, id);
    return TutorialMapper.convertTutorialsToDTOs(new HashSet<>(tutorialEntities));
  }

  public long countTutorialsByTheme(Long themeId) {
    return tutorialDao.countTutorialsByTheme(themeId);
  }
}
