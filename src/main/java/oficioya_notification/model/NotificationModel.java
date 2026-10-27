package oficioya_notification.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationModel {
    private UUID id;
    private UUID receiverId;
    private String title;
    private String description;
    private NotificationType type;
    private UUID requestId;
    private LocalDateTime dateSent;
    private LocalDateTime dateCreated;

    public NotificationModel(){

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
