package toDoApp.database.dao;

import org.hibernate.Session;
import toDoApp.Utils.HibernateUtil;
import toDoApp.database.entities.TaskEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
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
        return getByNativeQuery(query);
    }

    @Override
    public List<TaskEntity> getAllTaskEntitiesFromParentTask(String parentId) {
        String query = "db.TaskEntity.find({'parentTaskEntity_id' : ObjectId(\"" + parentId + "\")})";
        return getByNativeQuery(query);
    }

    @Override
    public List<TaskEntity> getTaskByDate(LocalDate date) {
        String query = "db.TaskEntity.find({'dueDate':" + date.toString() + "})";
        return getByNativeQuery(query);
    }

    @Override
    public List<TaskEntity> getOverdueTaskForNotification() {
        String query = "SELECT taskEntity FROM TaskEntity taskEntity WHERE taskEntity.notificationDate <:date " +
                "AND taskEntity.done=FALSE AND taskEntity.notify=TRUE";
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<TaskEntity> taskEntities = entityManager.createQuery(query, TaskEntity.class).
                setParameter("date",Date.from(Instant.now())).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return taskEntities;
    }

    @Override
    public TaskEntity getNextTaskEntityNotification() {
        String query = "SELECT taskEntity FROM TaskEntity taskEntity WHERE taskEntity.notify = TRUE AND taskEntity.notificationDate>:date " +
                "ORDER BY taskEntity.notificationDate";
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TaskEntity taskEntity = entityManager.createQuery(query, TaskEntity.class)
                .setParameter("date", Date.from(Instant.now()))
                .setMaxResults(1)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return taskEntity;
    }

    private List<TaskEntity> getByNativeQuery(String query){
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<TaskEntity> taskEntities = entityManager.createNativeQuery(query, TaskEntity.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return taskEntities;
    }
}
