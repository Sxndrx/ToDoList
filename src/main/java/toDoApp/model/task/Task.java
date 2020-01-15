package toDoApp.model.task;

import javafx.beans.property.*;
import toDoApp.database.task.TaskEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Task {

    private StringProperty id;
    private StringProperty title;
    private StringProperty description;
    private StringProperty dateString;
    private BooleanProperty priority;
    private BooleanProperty notify;
    private StringProperty notifyDateString;
    private StringProperty projectId;
    private StringProperty parentTaskId;

    public Task() {
        id = new SimpleStringProperty();
        title = new SimpleStringProperty();
        description = new SimpleStringProperty();
        dateString = new SimpleStringProperty();
        priority = new SimpleBooleanProperty();
        notify = new SimpleBooleanProperty();
        notifyDateString = new SimpleStringProperty();
        projectId = new SimpleStringProperty();
        parentTaskId = new SimpleStringProperty();

    }

    public Task(TaskEntity taskEntity) {
        id = new SimpleStringProperty(taskEntity.getId());
        title = new SimpleStringProperty(taskEntity.getId());
        if (taskEntity.getDescription() != null) {
            description = new SimpleStringProperty(taskEntity.getDescription());
        }
//        if (taskEntity.getDueDate() != null) {
//            dateString = new SimpleStringProperty(taskEntity.getDueDate().toString());
//        }
        priority = new SimpleBooleanProperty(taskEntity.getPriority().booleanValue());
//        notify = new SimpleBooleanProperty(taskEntity.getNotify().booleanValue());
//        if (notify.getValue()) {
//            notifyDateString = new SimpleStringProperty(taskEntity.getNotificationDate().toString());
//        }
        projectId = new SimpleStringProperty(taskEntity.getProjectEntity().getId());
        if(taskEntity.getParentTaskEntity()!=null){
            parentTaskId = new SimpleStringProperty(taskEntity.getParentTaskEntity().getId());
        }
    }


    public TaskEntity toTaskEntity() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(id.getValue());
        taskEntity.setTitle(title.getValue());
        if (description != null) {
            taskEntity.setDescription(description.getValue());
        }
        if (dateString != null) {
            try {
                taskEntity.setDueDate(new SimpleDateFormat().parse(dateString.getValue()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        ;
        taskEntity.setPriority(priority.get());
        taskEntity.setNotify(notify.get());
        if (notify.getValue()) {
            try {
                taskEntity.setNotificationDate(new SimpleDateFormat().parse(notifyDateString.getValue()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
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

    public String getDateString() {
        return dateString.get();
    }

    public StringProperty dateStringProperty() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString.set(dateString);
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

    public String getNotifyDateString() {
        return notifyDateString.get();
    }

    public StringProperty notifyDateStringProperty() {
        return notifyDateString;
    }

    public void setNotifyDateString(String notifyDateString) {
        this.notifyDateString.set(notifyDateString);
    }

    public String getProjectId() {
        return projectId.get();
    }

    public StringProperty projectIdProperty() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId.set(projectId);
    }

    public String getParentTaskId() {
        return parentTaskId.get();
    }

    public StringProperty parentTaskIdProperty() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId.set(parentTaskId);
    }
}