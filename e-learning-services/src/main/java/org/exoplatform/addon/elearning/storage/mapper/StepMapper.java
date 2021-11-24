package org.exoplatform.addon.elearning.storage.mapper;

import org.apache.commons.lang3.StringUtils;
import org.exoplatform.addon.elearning.dao.AttachmentDAO;
import org.exoplatform.addon.elearning.dao.ThemeDao;
import org.exoplatform.addon.elearning.dao.TutorialDao;
import org.exoplatform.addon.elearning.dto.Step;
import org.exoplatform.addon.elearning.entity.AttachmentEntity;
import org.exoplatform.addon.elearning.entity.StepEntity;
import org.exoplatform.addon.elearning.entity.TutorialEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StepMapper {

  private StepMapper() {
  }

  public static Step convertStepToDTO(StepEntity stepEntity) {
    if (stepEntity == null) {
      return null;
    }

    Step step = new Step();
    step.setId(stepEntity.getId());
    step.setTitle(stepEntity.getTitle());
    step.setContent(stepEntity.getContent());
    step.setImageFileId(stepEntity.getImageFileId());
    step.setMediaLink(stepEntity.getMediaLink());
    step.setOrder(stepEntity.getOrder());
    step.setTutorialId(stepEntity.getTutorial().getId());
    step.setAttachmentsIds(stepEntity.getAttachmentEntities().stream().map(AttachmentEntity::getId).collect(Collectors.toList()));
    return step;

  }

  public static StepEntity convertStepToEntity(Step step, ThemeDao themeDao, TutorialDao tutorialDao, AttachmentDAO attachmentDAO) {
    if (step == null) {
      return null;
    }

    StepEntity stepEntity = new StepEntity();
    stepEntity.setId(step.getId() == null || step.getId().equals(0L) ? null : step.getId());
    stepEntity.setTitle(step.getTitle());
    stepEntity.setContent(step.getContent());
    stepEntity.setImageFileId(step.getImageFileId() == null || step.getImageFileId().equals(0L) ? null : step.getImageFileId());
    stepEntity.setMediaLink(step.getMediaLink() == null || StringUtils.isEmpty(step.getMediaLink()) ? null : step.getMediaLink());
    stepEntity.setOrder(step.getOrder());
    if (step.getTutorialId() != null) {
      TutorialEntity tutorialEntity = tutorialDao.find(step.getTutorialId());
      stepEntity.setTutorial(tutorialEntity);
    }
    List<AttachmentEntity> attachmentEntities = new ArrayList<>();
    if (step.getAttachmentsIds() != null) {
      for (Long attachmentId : step.getAttachmentsIds()) {
        attachmentEntities.add(attachmentDAO.find(attachmentId));
      }
    }
    stepEntity.setAttachmentEntities(attachmentEntities);
    return stepEntity;

  }

  public static List<Step> convertStepsToDTOs(List<StepEntity> stepEntities) {
    if (stepEntities == null) {
      return new ArrayList<>();
    }
    return stepEntities.stream().map(StepMapper::convertStepToDTO).collect(Collectors.toList());

  }

  public static List<StepEntity> convertStepsToEntities(List<Step> steps, ThemeDao themeDao, TutorialDao tutorialDao, AttachmentDAO attachmentDAO) {
    if (steps == null) {
      return new ArrayList<>();
    }
    return steps.stream().map(step -> convertStepToEntity(step, themeDao, tutorialDao, attachmentDAO)).collect(Collectors.toList());

  }

}
