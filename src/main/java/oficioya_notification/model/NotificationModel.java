package oficioya_notification.model;

import java.time.LocalDateTime;
import java.util.UUID;
import java.time.LocalDateTime;

public class NotificationModel {
    private UUID id;
    private UUID receiverId;
    private UUID senderId;
    private String title;
    private String description;
    private String photoUrl;
    private LocalDateTime workDate;
    private String exactAddress;
    private NotificationType type;
    private UUID requestId;
    private LocalDateTime dateTimeCreated;

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
