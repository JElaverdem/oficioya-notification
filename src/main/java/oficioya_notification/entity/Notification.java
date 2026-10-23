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

    @Column(name = "sender_id", nullable = false)
    private UUID senderId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;
    private String photoUrl;

    @Column(name = "work_date", nullable = false)
    private LocalDateTime workDate;

    @Column(name = "exact_address", nullable = false)
    private String exactAddress;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(name = "request_id", nullable = false)
    private UUID requestId;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateTimeCreated;

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

    public void setSenderId(UUID newId){
        this.senderId = newId;
    }

    public UUID getSenderId(){
        return this.senderId;
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

    public void setWorkDate(LocalDateTime newDate){
        this.workDate = newDate;
    }

    public LocalDateTime getWorkDate(){
        return this.workDate;
    }

    public void setExactAddress(String newAdd){
        this.exactAddress = newAdd;
    }

    public String getExactAddress(){
        return this.exactAddress;
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

    public void setDateTimeCreated(LocalDateTime newDate){
        this.dateTimeCreated = newDate;
    }

    public LocalDateTime getDateTimeCreated(){
        return this.dateTimeCreated;
    }
}
