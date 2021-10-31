package org.exoplatform.addon.elearning.storage.mapper;

import org.exoplatform.addon.elearning.dao.StepDAO;
import org.exoplatform.addon.elearning.dto.Attachment;
import org.exoplatform.addon.elearning.entity.AttachmentEntity;

import java.util.Date;

public class AttachmentMapper {
  public AttachmentMapper() {
  }
  
  public static Attachment convertAttachmentToDTO(AttachmentEntity attachmentEntity) {
    if (attachmentEntity == null) {
      return null;
    }
    
    Attachment attachment = new Attachment();
    attachment.setId(attachmentEntity.getId());
    attachment.setFullTitle(attachmentEntity.getFullTitle());
    attachment.setCreatedDate(new Date(attachmentEntity.getCreatedDate().getTime()));
    attachment.setAttachmentFileID(attachmentEntity.getAttachmentFileID());
    if (attachmentEntity.getStepEntity() != null) {
      attachment.setStepId(attachmentEntity.getStepEntity().getId());
    }
    return attachment;
  }
  
  public static AttachmentEntity convertAttachmentToEntity(Attachment attachment, StepDAO stepDAO) {
    if (attachment == null) {
      return null;
    }
    
    AttachmentEntity attachmentEntity = new AttachmentEntity();
    attachmentEntity.setId(attachment.getId());
    attachmentEntity.setFullTitle(attachment.getFullTitle());
    attachmentEntity.setCreatedDate(new Date(attachment.getCreatedDate().getTime()));
    attachmentEntity.setAttachmentFileID(attachment.getAttachmentFileID());
    if (attachment.getStepId() != null) {
      attachmentEntity.setStepEntity(stepDAO.find(attachment.getStepId())); 
    }
    return attachmentEntity;
  }
}
