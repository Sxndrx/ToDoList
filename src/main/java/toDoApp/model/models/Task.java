package toDoApp.model.models;

import javafx.beans.property.*;
import toDoApp.Utils.Utils;
import toDoApp.database.entities.TaskEntity;

import java.time.LocalDateTime;

public class Task {

    private StringProperty id;
    private StringProperty title;
    private StringProperty description;
    private ObjectProperty<LocalDateTime> date;
    private BooleanProperty priority;
    private BooleanProperty notify;
    private ObjectProperty<LocalDateTime> notificationDate;
    private BooleanProperty done;
    private ObjectProperty<Project> project;
    private ObjectProperty<Task> parentTask;

    public Task() {
        id = new SimpleStringProperty();
        title = new SimpleStringProperty();
        description = new SimpleStringProperty();
        date = new SimpleObjectProperty<>();
        priority = new SimpleBooleanProperty();
        notify = new SimpleBooleanProperty();
        notificationDate = new SimpleObjectProperty<>();
        project = new SimpleObjectProperty<>();
        parentTask = new SimpleObjectProperty<>();
        done = new SimpleBooleanProperty(false);
    }

    public Task(TaskEntity taskEntity) {
        id = new SimpleStringProperty(taskEntity.getId());
        title = new SimpleStringProperty(taskEntity.getTitle());
        if (taskEntity.getDescription() != null) {
            description = new SimpleStringProperty(taskEntity.getDescription());
        }
        if (taskEntity.getDueDate() != null) {
            date = new SimpleObjectProperty<>(Utils.toLocalDateTime(taskEntity.getDueDate()));
        }
        priority = new SimpleBooleanProperty(taskEntity.getPriority());
        notify = new SimpleBooleanProperty(taskEntity.getNotify());
        if (getNotify()) {
            notificationDate = new SimpleObjectProperty<>(Utils.toLocalDateTime(taskEntity.getNotificationDate()));
        }
        done = new SimpleBooleanProperty(taskEntity.getDone());
        project = new SimpleObjectProperty<>(new Project(taskEntity.getProjectEntity()));
        if (taskEntity.getParentTaskEntity() != null) {
            parentTask = new SimpleObjectProperty<>(new Task(taskEntity.getParentTaskEntity()));
        }
    }


    public TaskEntity toTaskEntity() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(getId());
        taskEntity.setTitle(getTitle());
        if (description != null) {
            taskEntity.setDescription(getDescription());
        }
        if (date != null) {
            taskEntity.setDueDate(Utils.toDate(getDate()));
        }
        taskEntity.setPriority(getPriority());
        if (notify != null) {
            taskEntity.setNotify(getNotify());
        }
        if (getNotify()) {
            taskEntity.setNotificationDate(Utils.toDate(getNotificationDate()));
        }
        taskEntity.setDone(isDone());
        taskEntity.setProjectEntity(getProject().toProjectEntity());
        if (parentTask != null && getParentTask() != null) {
            taskEntity.setParentTaskEntity(getParentTask().toTaskEntity());
        }

        return taskEntity;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public boolean getPriority() {
        return priority.get();
    }

    public BooleanProperty priorityProperty() {
        return priority;
    }

    public boolean getNotify() {
        return notify.get();
    }

    public BooleanProperty notifyProperty() {
        return notify;
    }

    public LocalDateTime getDate() {
        return date.get();
    }

    public void setDate(LocalDateTime date) {
        this.date.set(date);
    }

    public ObjectProperty<LocalDateTime> dateProperty() {
        return date;
    }

    public boolean isPriority() {
        return priority.get();
    }

    public void setPriority(boolean priority) {
        this.priority.set(priority);
    }

    public boolean isNotify() {
        return notify.get();
    }

    public void setNotify(boolean notify) {
        this.notify.set(notify);
    }

    public Project getProject() {
        return project.get();
    }

    public void setProject(Project project) {
        this.project.set(project);
    }

    public ObjectProperty<Project> projectProperty() {
        return project;
    }

    public Task getParentTask() {
        return parentTask.get();
    }

    public void setParentTask(Task parentTask) {
        this.parentTask.set(parentTask);
    }

    public ObjectProperty<Task> parentTaskProperty() {
        return parentTask;
    }

    public boolean isDone() {
        return done.get();
    }

    public void setDone(boolean done) {
        this.done.set(done);
    }

    public BooleanProperty doneProperty() {
        return done;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate.get();
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        if (this.notificationDate == null) {
            this.notificationDate = new SimpleObjectProperty<>();
        }
        this.notificationDate.set(notificationDate);
    }

    public ObjectProperty<LocalDateTime> notificationDateProperty() {
        return notificationDate;
    }
}