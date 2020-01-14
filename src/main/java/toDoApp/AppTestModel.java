package toDoApp;

import toDoApp.database.project.IProjectDao;
import toDoApp.database.project.ProjectDao;
import toDoApp.database.task.ITaskDao;
import toDoApp.database.task.TaskDao;
import toDoApp.database.task.TaskEntity;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AppTestModel {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

        IProjectDao projectDao = new ProjectDao();
        ITaskDao taskDao = new TaskDao();

        List<TaskEntity> list = taskDao.getAllTaskEntitiesFromProject("5e1e2734d8895c81e963b59e");
        for(TaskEntity taskEntity : list){
            System.out.println(taskEntity.getTitle());
        }

        List<TaskEntity> list2 = taskDao.getAllTaskEntitiesFromParentTask("5e1e2dd6ad1cb78badfd37c4");
        for(TaskEntity taskEntity : list2){
            System.out.println(taskEntity.getTitle());
        }

        HibernateUtil.closeEntityManagerFactory();
    }
}
