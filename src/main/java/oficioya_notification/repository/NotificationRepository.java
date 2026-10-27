package oficioya_notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import oficioya_notification.entity.Notification;
import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID>{
    List<Notification> findByReceiverId(UUID receiverId);
    List<Notification> findByRequestId(UUID requestId);
}
