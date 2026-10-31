package oficioya_notification.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import oficioya_notification.dto.NotificationRequest;
import oficioya_notification.dto.NotificationResponse;
import oficioya_notification.entity.Notification;
import oficioya_notification.mapper.ResponseMapper;
import oficioya_notification.repository.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final ResponseMapper responseMapper;

    public NotificationService(NotificationRepository notificationRepository, ResponseMapper responseMapper){
        this.notificationRepository = notificationRepository;
        this.responseMapper = responseMapper;
    }

    public List<NotificationResponse> findAllForUser(UUID userId){
        return notificationRepository.findByReceiverId(userId).stream().map(responseMapper::toResponse).toList();
    }

    public Optional<NotificationResponse> findById(UUID id){
        Optional<Notification> response = notificationRepository.findById(id);
        if(response.isEmpty()){
            return Optional.empty();
        }
        else{
            return Optional.of(responseMapper.toResponse(response.orElse(null)));
        }
    }

    public List<NotificationResponse> findAllForARequest(UUID requestId, UUID receiverId){
        /*
        Verificar si existe o no la request, si no existe, mandar exception y hacer un handler
         */
        return notificationRepository.findByRequestIdAndReceiverId(requestId, receiverId).stream().map(responseMapper::toResponse).toList();
    }

    public NotificationResponse sendNotification(NotificationRequest request){
        /*
        Lógica de conexión a firebase API para enviar una notificacion
         */

        Notification noti = responseMapper.toNotification(request);
        noti.setDateSent(noti.getDateCreated());

        return responseMapper.toResponse(notificationRepository.save(noti));
    }

    public boolean deleteNotification(UUID requestId){
        if (!notificationRepository.existsById(requestId)){
            return false;
        }
        notificationRepository.deleteById(requestId);
        return true;
    }
    
}
