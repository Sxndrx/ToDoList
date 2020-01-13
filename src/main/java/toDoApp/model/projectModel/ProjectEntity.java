package toDoApp.model.projectModel;

import org.bson.types.ObjectId;
import toDoApp.model.taskModel.TaskEntity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;

    private String title;
    private String description;
    private Boolean priority;

    @ElementCollection(targetClass = TaskEntity.class)
    private List<TaskEntity> taskEntities = new LinkedList<>();


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

    public Boolean getPriority() {
        return priority;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    public List<TaskEntity> getTaskEntities() {
        return taskEntities;
    }

    public void setTaskEntities(List<TaskEntity> taskEntities) {
        this.taskEntities = taskEntities;
    }

    @Override
    public String toString() {
        return "Project : " + id.toString() + " name: " + title;
    }
}
