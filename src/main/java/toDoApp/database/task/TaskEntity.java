package toDoApp.database.task;

import org.hibernate.annotations.Type;
import toDoApp.database.project.ProjectEntity;

import javax.persistence.*;
import java.util.*;

@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "objectid")
    private String id;

    private String title;
    private String description;
    private Date dueDate;
    private Boolean priority;
    private Boolean notify;
    private Date notificationDate;

    @ManyToOne
    private ProjectEntity projectEntity;
    @ManyToOne
    private TaskEntity parentTaskEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public TaskEntity getParentTaskEntity() {
        return parentTaskEntity;
    }

    public void setParentTaskEntity(TaskEntity parentTaskEntity) {
        this.parentTaskEntity = parentTaskEntity;
    }
}
