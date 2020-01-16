package toDoApp.database.task;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

public interface ITaskDao {
    void addTaskEntity(TaskEntity taskEntity);
    void removeTaskEntity(TaskEntity taskEntity);
    void updateTaskEntity(TaskEntity taskEntity);
    List<TaskEntity> getAllTaskEntitiesFromProject(String projectId);
    List<TaskEntity> getAllTaskEntitiesFromParentTask(String parentId);
    List<TaskEntity> getTaskByDate(LocalDate date);
}
