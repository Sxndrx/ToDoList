package toDoApp.database.task;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Cache;
import toDoApp.database.project.ProjectEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "objectid")
    private String id;

    private String title;
    private String description;
    private LocalDate dueDate;
    private Boolean priority;
    private Boolean notify;
    private Boolean done;

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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
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

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
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
