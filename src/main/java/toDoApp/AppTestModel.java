package toDoApp;

import toDoApp.model.projectModel.IProjectDao;
import toDoApp.model.projectModel.ProjectDao;
import toDoApp.model.projectModel.ProjectEntity;
import toDoApp.model.taskModel.ITaskDao;
import toDoApp.model.taskModel.Task;
import toDoApp.model.taskModel.TaskDao;
import toDoApp.model.taskModel.TaskEntity;

import javax.persistence.EntityManagerFactory;
import java.util.LinkedList;
import java.util.List;

public class AppTestModel {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

        IProjectDao projectDao = new ProjectDao();
        ITaskDao taskDao = new TaskDao();

        ProjectEntity projectEntity1 = new ProjectEntity();
        projectEntity1.setTitle("Help me");
        projectEntity1.setDescription("I need help ASAP");
        projectEntity1.setPriority(true);
        projectDao.addProjectEntity(projectEntity1);



        TaskEntity task1 = new TaskEntity();
        task1.setTitle("1");
        taskDao.addTaskEntity(task1);
        TaskEntity task2 = new TaskEntity();
        task2.setTitle("2");
        taskDao.addTaskEntity(task2);
        TaskEntity task3 = new TaskEntity();
        task3.setTitle("3");
        taskDao.addTaskEntity(task3);
        List<TaskEntity> taskEntities = new LinkedList<>();
        taskEntities.add(task1);
        taskEntities.add(task2);
        taskEntities.add(task3);

        ProjectEntity projectEntity2 = new ProjectEntity();
        projectEntity2.setTitle("To do a TO DO LIST kur...");
        projectEntity2.setTaskEntities(taskEntities);
        projectDao.addProjectEntity(projectEntity2);

        HibernateUtil.closeEntityManagerFactory();
    }
}
