package tn.esprit.User.AppResouces.Repository;

import tn.esprit.User.AppResouces.Models.Entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
