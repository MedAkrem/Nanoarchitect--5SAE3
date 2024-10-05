package tn.esprit.User.AppResouces.Services.interfaces;


import tn.esprit.User.AppResouces.Exceptions.DemandeException;
import tn.esprit.User.AppResouces.Models.Entities.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AttachmentService {
    public Attachment addAttachment(MultipartFile file, Long demId) throws IOException, DemandeException;
    public Attachment deleteAttachment(Long attId) throws IOException;
}
