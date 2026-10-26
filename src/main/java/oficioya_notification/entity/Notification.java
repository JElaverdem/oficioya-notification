package oficioya_notification.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import oficioya_notification.model.NotificationType;

@Entity 
@Table(name = "Notifications")
public class Notification {
    @Id
    private UUID id;

    @Column(name = "receiver_id", nullable = false)
    private UUID receiverId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(name = "request_id", nullable = false)
    private UUID requestId;

    @Column(name = "date_sent", nullable = false)
    private LocalDateTime dateSent;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    public Notification(){

    }

    public void setId(UUID newId){
        this.id = newId;
    }

    public UUID getId(){
        return this.id;
    }

    public void setReceiverId(UUID newId){
        this.receiverId = newId;
    }

    public UUID getReceiverId(){
        return this.receiverId;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public String getTitle(){
        return this.title;
    }

    public void setDescription(String newDes){
        this.description = newDes;
    }

    public String getDescription(){
        return this.description;
    }

    public void setState(NotificationType newType){
        this.type = newType;
    }

    public NotificationType getState(){
        return this.type;
    }

    public void setRequestId(UUID newId){
        this.requestId = newId;
    }

    public UUID getRequestId(){
        return this.requestId;
    }

     public void setDateSent(LocalDateTime newDate){
        this.dateSent = newDate;
    }

    public LocalDateTime getDateSent(){
        return this.dateSent;
    }

    public void setDateCreated(LocalDateTime newDate){
        this.dateCreated = newDate;
    }

    public LocalDateTime getDateCreated(){
        return this.dateCreated;
    }
}
