package toDoApp.database.task;

import org.bson.types.ObjectId;
import org.hibernate.Session;
import toDoApp.HibernateUtil;
import toDoApp.model.task.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class TaskDao implements ITaskDao {
    @Override
    public void addTaskEntity(TaskEntity taskEntity) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            entityManager.persist(taskEntity);
            transaction.commit();
            entityManager.flush();
        }catch (Exception e){
            transaction.rollback();
        }
        entityManager.close();
    }

    @Override
    public TaskEntity getTaskEntity(ObjectId taskId) {
        return null;
    }

    @Override
    public void removeTaskEntity(TaskEntity taskEntity) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            entityManager.refresh(taskEntity);
            entityManager.remove(taskEntity);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        entityManager.close();
    }

    @Override
    public void updateTaskEntity(TaskEntity taskEntity) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.unwrap(Session.class).merge(taskEntity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<TaskEntity> getAllTaskEntitiesFromProject(String projectId) {
        String query = "db.TaskEntity.find({'projectEntity_id' : ObjectId(\"" + projectId + "\")})";
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<TaskEntity> taskEntities = entityManager.createNativeQuery(query, TaskEntity.class).getResultList();
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getAllTaskEntitiesFromParentTask(String parentId) {
        String query = "db.TaskEntity.find({'parentTaskEntity_id' : ObjectId(\"" + parentId + "\")})";
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<TaskEntity> taskEntities = entityManager.createNativeQuery(query, TaskEntity.class).getResultList();
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return taskEntities;
    }
}
