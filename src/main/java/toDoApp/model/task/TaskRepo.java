package toDoApp.model.task;

import javafx.application.Platform;
import toDoApp.database.task.ITaskDao;
import toDoApp.database.task.TaskDao;
import toDoApp.database.task.TaskEntity;

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
        List<Task> subTasks = getTaskListFromTaskEntities(subTasksEntities);
        return subTasks;
    }

    public static List<Task> getTasksFromProject(String projectId){
        List<TaskEntity> taskEntities = taskDao.getAllTaskEntitiesFromProject(projectId);
        List<Task> tasks = getTaskListFromTaskEntities(taskEntities);
        return tasks;
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
        Platform.runLater(()->{
            taskDao.addTaskEntity(taskEntity);
        });
    }
    public static void removeTask(Task task){
        List<Task> subTasks = getSubTasksFromParent(task);
        for(Task subTask: subTasks){
            removeTask(subTask);
        }
    }


}
