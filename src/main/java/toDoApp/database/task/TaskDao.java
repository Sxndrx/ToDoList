package toDoApp.database.task;

import org.hibernate.Session;
import toDoApp.Utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
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
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        entityManager.close();
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
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            entityManager.unwrap(Session.class).merge(taskEntity);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        entityManager.close();
    }

    @Override
    public List<TaskEntity> getAllTaskEntitiesFromProject(String projectId) {
        String query = "db.TaskEntity.find({$and:[{'projectEntity_id' : ObjectId(\"" + projectId + "\")}," +
                "{'parentTaskEntity_id':{$exists:false}}] })";
        return getByQuery(query);
    }

    @Override
    public List<TaskEntity> getAllTaskEntitiesFromParentTask(String parentId) {
        String query = "db.TaskEntity.find({'parentTaskEntity_id' : ObjectId(\"" + parentId + "\")})";
        return getByQuery(query);
    }

    @Override
    public List<TaskEntity> getTaskByDate(LocalDate date) {
        String query = "db.TaskEntity.find({'dueDate':" + date.toString() + "})";
        return getByQuery(query);
    }

    private List<TaskEntity> getByQuery(String query){
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<TaskEntity> taskEntities = entityManager.createNativeQuery(query, TaskEntity.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return taskEntities;
    }
}
