package toDoApp.model.taskModel;

import org.bson.types.ObjectId;
import org.hibernate.Session;
import toDoApp.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

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
}
