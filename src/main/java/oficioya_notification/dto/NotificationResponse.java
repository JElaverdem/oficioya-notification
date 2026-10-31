package oficioya_notification.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationResponse (
    UUID id,
    String title,
    String description,
    LocalDateTime dateCreated,
    LocalDateTime dateSent
    ){
    
}
