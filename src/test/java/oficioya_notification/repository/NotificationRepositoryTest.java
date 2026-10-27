package oficioya_notification.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import oficioya_notification.entity.Notification;
import oficioya_notification.model.NotificationModel;
import oficioya_notification.mapper.NotificationMapper;
import oficioya_notification.model.NotificationType;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@DataJpaTest
@ActiveProfiles("test")
public class NotificationRepositoryTest {
    @Autowired 
    private NotificationRepository nRepo;

    private final NotificationMapper nMap = Mappers.getMapper(NotificationMapper.class);

    private Notification n1;

    @BeforeEach 
    void setup(){
        UUID n1NotId = UUID.randomUUID();
        UUID n1RecId = UUID.randomUUID();
        UUID n1ReqId = UUID.randomUUID();
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime sent = LocalDateTime.now().plusSeconds(2);
        n1 = new Notification();
        n1.setId(n1NotId);
        n1.setReceiverId(n1RecId);
        n1.setRequestId(n1ReqId);
        n1.setDateCreated(created);
        n1.setDateSent(sent);
        n1.setState(NotificationType.INCOMING_REQUEST);
        n1.setTitle("Solicitu Entrante");
        n1.setDescription("Nueva reparación cerca de tí");
    }

    @Test 
    @DisplayName("Salvar notification básica")
    void shouldSaveBasicNotification(){
        Notification saved = nRepo.save(n1);

        assertNotNull(saved);
        assertEquals(n1.getTitle(),saved.getTitle());
        assertEquals(n1.getId(),saved.getId());
        assertEquals(n1.getDescription(),saved.getDescription());
        assertEquals(n1.getReceiverId(),saved.getReceiverId());
        assertEquals(n1.getRequestId(),saved.getRequestId());
    }

    @Test 
    @DisplayName ("Salvar dos notificaciones con mismo recipiente")
    void shouldSaveTwoNotificationsToSameReceiver(){
        UUID n1NotId = UUID.randomUUID();
        UUID n1RecId = n1.getReceiverId();
        UUID n1ReqId = UUID.randomUUID();
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime sent = LocalDateTime.now().plusSeconds(2);
        Notification n2 = new Notification();
        n2.setId(n1NotId);
        n2.setReceiverId(n1RecId);
        n2.setRequestId(n1ReqId);
        n2.setDateCreated(created);
        n2.setDateSent(sent);
        n2.setState(NotificationType.INCOMING_REQUEST);
        n2.setTitle("Solicitu Entrante");
        n2.setDescription("Nueva reparación cerca de tí");

        Notification saved = nRepo.save(n1);
        Notification saved2 = nRepo.save(n2);

        assertNotNull(saved);
        assertEquals(n1.getTitle(),saved.getTitle());
        assertEquals(n1.getId(),saved.getId());
        assertEquals(n1.getDescription(),saved.getDescription());
        assertEquals(n1.getReceiverId(),saved.getReceiverId());
        assertEquals(n1.getRequestId(),saved.getRequestId());

        List<Notification> ls = nRepo.findByReceiverId(n1RecId);

        assertEquals(ls.size(),2);
        assertTrue(ls.contains(saved));
        assertTrue(ls.contains(saved2));
        assertEquals(ls.get(0).getReceiverId(),n1.getReceiverId());
        assertEquals(ls.get(1).getReceiverId(),n1.getReceiverId());
    }

    @Test 
    @DisplayName ("Salvar dos notificaciones de distinto tipo")
    void shouldSaveTwoNotificationsDifferenteType(){
        UUID n1NotId = UUID.randomUUID();
        UUID n1RecId = n1.getReceiverId();
        UUID n1ReqId = UUID.randomUUID();
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime sent = LocalDateTime.now().plusSeconds(2);
        Notification n2 = new Notification();
        n2.setId(n1NotId);
        n2.setReceiverId(n1RecId);
        n2.setRequestId(n1ReqId);
        n2.setDateCreated(created);
        n2.setDateSent(sent);
        n2.setState(NotificationType.ACCEPTED_REQUEST);
        n2.setTitle("Solicitu Aceptada");
        n2.setDescription("Carlos ramírez atenderá tu solicitud");

        Notification saved = nRepo.save(n1);
        Notification saved2 = nRepo.save(n2);

        List<Notification> ls = nRepo.findByReceiverId(n1RecId);
        assertTrue(ls.contains(saved));
        assertTrue(ls.contains(saved2));

        assertEquals(ls.get(0).getState(),NotificationType.INCOMING_REQUEST);
        assertEquals(ls.get(1).getState(),NotificationType.ACCEPTED_REQUEST);

        assertEquals(ls.get(1).getDateCreated(),created);
        assertEquals(ls.get(1).getDateSent(),sent);
    }

    @Test 
    @DisplayName ("Debe mappear de entidad a modelo")
    void shouldMapBetweenEntityAndModel(){
        nRepo.save(n1);

        List<Notification> ls = nRepo.findByReceiverId(n1.getReceiverId());

        List<NotificationModel> modelList = ls.stream().map(nMap::toModel).collect(Collectors.toList());

        Notification saved = ls.get(0);
        NotificationModel model = modelList.get(0);

        assertEquals(saved.getId(),model.getId());
        assertEquals(saved.getReceiverId(),model.getReceiverId());
        assertEquals(saved.getTitle(),model.getTitle());
        assertEquals(saved.getDescription(),model.getDescription());
        assertEquals(saved.getState(), model.getState());
        assertEquals(saved.getDateCreated(),model.getDateCreated());
        assertEquals(saved.getDateSent(),model.getDateSent());
        assertEquals(saved.getRequestId(),model.getRequestId());
    }

}
