package oficioya_notification.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import oficioya_notification.model.NotificationType;

public record NotificationResponse (
    UUID id,
    String title,
    String description,
    NotificationType type,
    UUID requestId,
    LocalDateTime dateCreated,
    LocalDateTime dateSent
    ){
    
}
