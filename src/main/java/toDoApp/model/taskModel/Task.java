package toDoApp.model.taskModel;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.bson.types.ObjectId;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Task {

    private StringProperty idProperty;
    private StringProperty titleProperty;
    private StringProperty descriptionProperty;
    private StringProperty dateProperty;
    private BooleanProperty priorityProperty;
    private BooleanProperty notifyProperty;
    private StringProperty notDateProperty;
    private ObservableList<Task> subTaskList;

    public Task(TaskEntity taskEntity) {
        idProperty = new SimpleStringProperty(taskEntity.getId().toString());
        titleProperty = new SimpleStringProperty(taskEntity.getId().toString());
        if (taskEntity.getDescription() != null) {
            descriptionProperty = new SimpleStringProperty("");
        }
        if (taskEntity.getDueDate() != null) {
            dateProperty = new SimpleStringProperty(taskEntity.getDueDate().toString());
        }
        priorityProperty = new SimpleBooleanProperty(taskEntity.getPriority().booleanValue());
        notifyProperty = new SimpleBooleanProperty(taskEntity.getNotify().booleanValue());
        if (notifyProperty.getValue()) {
            notDateProperty = new SimpleStringProperty(taskEntity.getNotificationDate().toString());
        }
        if (taskEntity.getSubTaskEntities() != null) {
            subTaskList = createTaskList(taskEntity.getSubTaskEntities());
        }
    }

    private ObservableList<Task> createTaskList(List<TaskEntity> taskEntities) {
        ObservableList<Task> tasks = new SimpleListProperty<>();
        for (TaskEntity taskEntity : taskEntities) {
            tasks.add(new Task(taskEntity));
        }
        return tasks;
    }

    public TaskEntity toTaskEntity() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(new ObjectId(idProperty.toString()));
        taskEntity.setTitle(titleProperty.toString());
        if (descriptionProperty != null) {
            taskEntity.setDescription(descriptionProperty.toString());
        }
        if (dateProperty != null) {
            try {
                taskEntity.setDueDate(new SimpleDateFormat().parse(dateProperty.toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        ;
        taskEntity.setPriority(priorityProperty.get());
        taskEntity.setNotify(notifyProperty.get());
        if (notifyProperty.getValue()) {
            try {
                taskEntity.setNotificationDate(new SimpleDateFormat().parse(notDateProperty.toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (subTaskList != null) {

        }
        return taskEntity;
    }

    private List<TaskEntity> createTaskEntities() {
        List<TaskEntity> taskEntities = new LinkedList<>();
        for (Task task : subTaskList) {
            taskEntities.add(task.toTaskEntity());
        }
        return taskEntities;
    }

    public void setIdProperty(String idProperty) {
        this.idProperty.set(idProperty);
    }

    public void setTitleProperty(String titleProperty) {
        this.titleProperty.set(titleProperty);
    }

    public void setDescriptionProperty(String descriptionProperty) {
        this.descriptionProperty.set(descriptionProperty);
    }

    public void setDateProperty(String dateProperty) {
        this.dateProperty.set(dateProperty);
    }

    public void setPriorityProperty(boolean priorityProperty) {
        this.priorityProperty.set(priorityProperty);
    }

    public void setNotifyProperty(boolean notifyProperty) {
        this.notifyProperty.set(notifyProperty);
    }

    public void setNotDateProperty(String notDateProperty) {
        this.notDateProperty.set(notDateProperty);
    }

    public void setSubTaskList(ObservableList<Task> subTaskList) {
        this.subTaskList = subTaskList;
    }
}