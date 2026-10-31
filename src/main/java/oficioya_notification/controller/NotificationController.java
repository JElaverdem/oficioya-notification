package oficioya_notification.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import oficioya_notification.dto.NotificationRequest;
import oficioya_notification.dto.NotificationResponse;
import oficioya_notification.service.NotificationService;

@RestController 
@RequestMapping("/api/v1/notification")
@Tag(name="Notifications", description = "API to control the notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a notification by its id", description = "Gets the dto of the notification by the id given")
    @PreAuthorize("hasAnyRole('CONTRATANTE', 'TRABAJADOR')")
    public ResponseEntity<NotificationResponse> getById(@PathVariable UUID id){
        Optional<NotificationResponse> found = notificationService.findById(id);
        
        if(!found.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(found.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(found.get());
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Get the notifications of a user", description = "Gets the notifications of a user by the id of the user")
    @PreAuthorize("hasAnyRole('CONTRATANTE', 'TRABAJADOR')")
    public ResponseEntity<List<NotificationResponse>> getAllUserNotifications(@PathVariable UUID id){
        /*
        Lógica de buscar id de usuario con servicio de usuarios
         */
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.findAllForUser(id));
    }

    @GetMapping("/user/{userId}/request/{requestId}")
    @Operation(summary="Get the notification of a request", description = "Gets the notifications of a user associated with a notification")
    @PreAuthorize("hasAnyRole('CONTRATANTE', 'TRABAJADOR')")
    public ResponseEntity<List<NotificationResponse>> getAllUserNotificationsForARequest(@PathVariable UUID userId, @PathVariable UUID requestId){
        /*
        Lógica de buscar el id de usuario con servicio de usuarios
         */
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.findAllForARequest(requestId, userId));
    }

    @PostMapping 
    @Operation(summary="Send a notification", description = "Sends a notification and stores it in the database")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<NotificationResponse> send(@Valid @RequestBody NotificationRequest notification){
        NotificationResponse response = notificationService.sendNotification(notification);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    
    @DeleteMapping("/{id}")
    @Operation(summary="Delete a notification", description="Deletes a notification with its id, only an admin can do it")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteNotification(@PathVariable UUID id){
        boolean could = notificationService.deleteNotification(id);

        if(could){
            return ResponseEntity.status(HttpStatus.OK).body("Notification deleted");
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found");
    }

}
