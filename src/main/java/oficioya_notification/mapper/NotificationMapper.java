package oficioya_notification.mapper;

import oficioya_notification.entity.Notification;
import oficioya_notification.model.NotificationModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    Notification toEntity(NotificationModel notificationModel);
    NotificationModel toModel(Notification notification);
}
