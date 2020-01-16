package toDoApp.model.task;

import javafx.beans.property.*;
import toDoApp.database.task.TaskEntity;
import toDoApp.model.project.Project;
import toDoApp.model.project.ProjectRepo;

import java.time.LocalDate;

public class Task {

    private StringProperty id;
    private StringProperty title;
    private StringProperty description;
    private ObjectProperty<LocalDate> date;
    private BooleanProperty priority;
    private BooleanProperty notify;
    private ObjectProperty<LocalDate> notifyDate;
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
        notifyDate = new SimpleObjectProperty<>();
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
            date = new SimpleObjectProperty<>(taskEntity.getDueDate());
        }
        priority = new SimpleBooleanProperty(taskEntity.getPriority().booleanValue());
        notify = new SimpleBooleanProperty(taskEntity.getNotify().booleanValue());
        if (taskEntity.getNotificationDate()!=null) {
            notifyDate = new SimpleObjectProperty<>(taskEntity.getNotificationDate());
        }
        done = new SimpleBooleanProperty(taskEntity.getDone());
        if(taskEntity.getProjectEntity()!=null){
            project = new SimpleObjectProperty<>(new Project(taskEntity.getProjectEntity()));
        }
        if(taskEntity.getParentTaskEntity()!=null){
            parentTask = new SimpleObjectProperty<>(new Task(taskEntity.getParentTaskEntity()));
        }
    }


    public TaskEntity toTaskEntity() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(id.getValue());
        taskEntity.setTitle(title.getValue());
        if (description != null) {
            taskEntity.setDescription(description.getValue());
        }
        if (date != null) {
            taskEntity.setDueDate(date.getValue());
        }
        taskEntity.setPriority(priority.get());
        if(notify!=null){
            taskEntity.setNotify(notify.get());
        }
        if (notifyDate!=null) {
            taskEntity.setNotificationDate(notifyDate.getValue());
        }
        taskEntity.setDone(done.getValue());
        if(project!=null){
            taskEntity.setProjectEntity(project.get().toProjectEntity());
        }else if(parentTask!=null){
            taskEntity.setParentTaskEntity(parentTask.get().toTaskEntity());
        }

        return taskEntity;
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public boolean getPriority() {
        return priority.get();
    }

    public BooleanProperty priorityProperty() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority.set(priority);
    }

    public boolean getNotify() {
        return notify.get();
    }

    public BooleanProperty notifyProperty() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify.set(notify);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public boolean isPriority() {
        return priority.get();
    }

    public boolean isNotify() {
        return notify.get();
    }

    public LocalDate getNotifyDate() {
        return notifyDate.get();
    }

    public ObjectProperty<LocalDate> notifyDateProperty() {
        return notifyDate;
    }

    public void setNotifyDate(LocalDate notifyDate) {
        this.notifyDate.set(notifyDate);
    }

    public Project getProject() {
        return project.get();
    }

    public ObjectProperty<Project> projectProperty() {
        return project;
    }

    public void setProject(Project project) {
        this.project.set(project);
    }

    public Task getParentTask() {
        return parentTask.get();
    }

    public ObjectProperty<Task> parentTaskProperty() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask.set(parentTask);
    }

    public boolean isDone() {
        return done.get();
    }

    public BooleanProperty doneProperty() {
        return done;
    }

    public void setDone(boolean done) {
        this.done.set(done);
    }
}