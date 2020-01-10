package model;

import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;

    private String title;
    private String description;
    private int priority;

    @ElementCollection
    private Map<ObjectId, Task> tasks = new HashMap<>();


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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Map<ObjectId, Task> getTasks() {
        return tasks;
    }

    public void setTasks(Map<ObjectId, Task> tasks) {
        this.tasks = tasks;
    }
}
