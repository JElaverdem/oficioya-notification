package oficioya_notification.mapper;

import org.springframework.stereotype.Component;

import oficioya_notification.dto.NotificationResponse;
import oficioya_notification.entity.Notification;

@Component
public class ResponseMapper {
    
    public NotificationResponse toResponse(Notification notification){
        return new NotificationResponse(
            notification.getId(),
            notification.getTitle(),
            notification.getDescription(),
            notification.getDateCreated(),
            notification.getDateSent()
        );
    }
}
