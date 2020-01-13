package toDoApp.model.projectModel;

import toDoApp.model.taskModel.TaskEntity;
import org.bson.types.ObjectId;

import java.util.List;

public interface IProjectDao {
    void addProjectEntity(ProjectEntity projectEntity);
    void removeProjectEntity(ProjectEntity projectEntity);
    void updateProjectEntity(ProjectEntity projectEntity);
    List<ProjectEntity> getAllProjectEntities();
}
