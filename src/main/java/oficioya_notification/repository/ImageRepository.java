package oficioya_notification.repository;

import oficioya_notification.model.document.ImageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends MongoRepository<ImageDocument, String> {
    List<ImageDocument> findByNotificationId(UUID notificationId);
    List<ImageDocument> findByReferenciaExterna(String referenciaExterna);
}