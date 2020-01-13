package toDoApp.model.taskModel;

import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.*;

@Entity
@Embeddable
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;

    private String title;
    private String description;
    private Date dueDate;
    private Boolean priority;
    private Boolean notify;
    private Date notificationDate;
    @ElementCollection(targetClass = TaskEntity.class)
    private List<TaskEntity> subTaskEntities = new ArrayList<>();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getPriority() {
        return priority;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    public Boolean getNotify() {
        return notify;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public List<TaskEntity> getSubTaskEntities() {
        return subTaskEntities;
    }

    public void setSubTaskEntities(List<TaskEntity> subTaskEntities) {
        this.subTaskEntities = subTaskEntities;
    }
}
