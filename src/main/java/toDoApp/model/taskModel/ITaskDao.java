package toDoApp.model.taskModel;

import org.bson.types.ObjectId;

public interface ITaskDao {
    void addTaskEntity(TaskEntity taskEntity);
    TaskEntity getTaskEntity(ObjectId taskId);
    void removeTaskEntity(TaskEntity taskEntity);
    void updateTaskEntity(TaskEntity taskEntity);
}
