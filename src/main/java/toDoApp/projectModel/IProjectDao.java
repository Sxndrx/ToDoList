package toDoApp.projectModel;

import toDoApp.taskModel.Task;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface IProjectDao {
    void addProject(Project project);
    void removeProject(Project project);
    List<Task> getTasks(ObjectId id);
    void updateProject(Project project);
    List<Project> getAllProjects();
}
