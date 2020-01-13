package toDoApp.model.projectModel;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import org.bson.types.ObjectId;
import toDoApp.model.taskModel.Task;
import toDoApp.model.taskModel.TaskEntity;

import java.util.LinkedList;
import java.util.List;

public class Project {

    private StringProperty idProperty;
    private StringProperty titleProperty;
    private StringProperty descriptionProperty;
    private BooleanProperty priorityProperty;
    private ObservableList<Task> taskObservableList;

    public Project(ProjectEntity projectEntity) {
        idProperty = new SimpleStringProperty(projectEntity.getId().toString());
        titleProperty = new SimpleStringProperty(projectEntity.getTitle());

        if(projectEntity.getDescription()!=null){
            descriptionProperty = new SimpleStringProperty(projectEntity.getTitle());
        }

        priorityProperty = new SimpleBooleanProperty(projectEntity.getPriority().booleanValue());

        if(projectEntity.getTaskEntities()!=null){
            taskObservableList = createTaskList(projectEntity.getTaskEntities());
        }
    }

    private ObservableList<Task> createTaskList(List<TaskEntity> taskEntities){
        ObservableList<Task> list = new SimpleListProperty<>();
        for(TaskEntity entity : taskEntities){
            Task task = new Task(entity);
            list.add(task);
        }
        return list;
    }

    public void toProjectEntity(){
        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setId(new ObjectId(idProperty.getValue()));
        projectEntity.setTitle(titleProperty.getValue());

        if(descriptionProperty != null){
            projectEntity.setDescription(descriptionProperty.getValue());
        }

        projectEntity.setPriority(priorityProperty.getValue());

        if(taskObservableList != null){
            projectEntity.setTaskEntities(createTaskEntityList());
        }
    }

    private List<TaskEntity> createTaskEntityList(){
        List<TaskEntity> taskEntities = new LinkedList<>();
        for(Task task : taskObservableList){
            TaskEntity taskEntity = task.toTaskEntity();
            taskEntities.add(taskEntity);
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

    public void setPriorityProperty(boolean priorityProperty) {
        this.priorityProperty.set(priorityProperty);
    }

    public void setTaskObservableList(ObservableList<Task> taskObservableList) {
        this.taskObservableList = taskObservableList;
    }

    @Override
    public String toString() {
        return titleProperty.toString();
    }
}
