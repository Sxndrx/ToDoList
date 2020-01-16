package toDoApp;

import toDoApp.Utils.HibernateUtil;
import toDoApp.database.dao.IProjectDao;
import toDoApp.database.dao.ProjectDao;
import toDoApp.database.dao.ITaskDao;
import toDoApp.database.dao.TaskDao;

import javax.persistence.EntityManagerFactory;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class AppTestModel {
    public static void main(String[] args) {
//        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
//
//        IProjectDao projectDao = new ProjectDao();
//        ITaskDao taskDao = new TaskDao();
//
////        List<TaskEntity> list = taskDao.getAllTaskEntitiesFromProject("5e1e2734d8895c81e963b59e");
////        for(TaskEntity taskEntity : list){
////            System.out.println(taskEntity.getTitle());
////        }
////
////        List<TaskEntity> list2 = taskDao.getAllTaskEntitiesFromParentTask("5e1e2dd6ad1cb78badfd37c4");
////        for(TaskEntity taskEntity : list2){
////            System.out.println(taskEntity.getTitle());
////        }
//
//        HibernateUtil.closeEntityManagerFactory();

        System.out.println(Date.from(Instant.now()).toString());


    }
}
