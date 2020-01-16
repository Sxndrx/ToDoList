package toDoApp.model.repo;

import toDoApp.database.dao.ITaskDao;
import toDoApp.database.dao.TaskDao;
import toDoApp.database.entities.TaskEntity;
import toDoApp.model.models.Task;

import java.util.LinkedList;
import java.util.List;

public class TaskRepo {

    private static ITaskDao taskDao = new TaskDao();

    public static void removeTasksFromProject(String projectId){
        for(Task task : getTasksFromProject(projectId)){
            removeTask(task);
        }
    }

    public static List<Task> getSubTasksFromParent(Task parent){
        List<TaskEntity> subTasksEntities = taskDao.getAllTaskEntitiesFromParentTask(parent.getId());
        return getTaskListFromTaskEntities(subTasksEntities);
    }

    public static List<Task> getTasksFromProject(String projectId){
        List<TaskEntity> taskEntities = taskDao.getAllTaskEntitiesFromProject(projectId);
        return getTaskListFromTaskEntities(taskEntities);
    }

    private static List<Task> getTaskListFromTaskEntities(List<TaskEntity> taskEntities){
        List<Task> tasks = new LinkedList<>();
        for(TaskEntity taskEntity : taskEntities){
            tasks.add(new Task(taskEntity));
        }
        return tasks;
    }

    public static void addTask(Task task){
        TaskEntity taskEntity = task.toTaskEntity();
            taskDao.addTaskEntity(taskEntity);
            task.setId(taskEntity.getId());
    }
    public static void removeTask(Task task){
        List<Task> subTasks = getSubTasksFromParent(task);
        for(Task subTask: subTasks){
            removeTask(subTask);
        }
        taskDao.removeTaskEntity(task.toTaskEntity());

    }
    public static void updateTask(Task task){
        taskDao.updateTaskEntity(task.toTaskEntity());
    }


    public static List<Task> getTaskListForNotification() {
        List<TaskEntity> list = taskDao.getOverdueTaskForNotification();
        return getTaskListFromTaskEntities(list);
    }

    public static Task getNextTaskNotification(){
        return new Task(taskDao.getNextTaskEntityNotification());
    }

}
