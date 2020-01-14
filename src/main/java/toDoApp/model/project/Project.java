package toDoApp.model.project;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import toDoApp.database.project.ProjectEntity;

public class Project {

    private StringProperty id;
    private StringProperty title;
    private StringProperty description;
    private BooleanProperty priority;

    public Project() {
        id = new SimpleStringProperty();
        title = new SimpleStringProperty();
        description = new SimpleStringProperty();
        priority = new SimpleBooleanProperty();
    }

    public Project(ProjectEntity projectEntity) {
        id = new SimpleStringProperty(projectEntity.getId());
        title = new SimpleStringProperty(projectEntity.getTitle());

        if (projectEntity.getDescription() != null) {
            description = new SimpleStringProperty(projectEntity.getTitle());
        }

        priority = new SimpleBooleanProperty(projectEntity.getPriority().booleanValue());

    }

    public ProjectEntity toProjectEntity() {
        ProjectEntity projectEntity = new ProjectEntity();

        if (id != null) {
            projectEntity.setId(id.getValue());
        }
        if (title != null) {
            projectEntity.setTitle(title.getValue());
        }

        if (description != null) {
            projectEntity.setDescription(description.getValue());
        }

        if (priority != null) {
            projectEntity.setPriority(priority.getValue());
        }

        return projectEntity;
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

    @Override
    public String toString() {
        return title.getValue();
    }
}
