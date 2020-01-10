package model;

import org.bson.types.ObjectId;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Embeddable
public class Task {
    @Id
    private ObjectId id;

    private String title;
    private String description;
    private Date dueDate;
    private Integer priority;
    private Boolean notify;
    private Date notificationDate;
    @ElementCollection
    private Map<ObjectId, Task> subTasks = new HashMap<>();


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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
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

    public Map<ObjectId, Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(Map<ObjectId, Task> subTasks) {
        this.subTasks = subTasks;
    }
}
