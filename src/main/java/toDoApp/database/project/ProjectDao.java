package toDoApp.database.project;

import org.hibernate.Session;
import toDoApp.Utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao implements IProjectDao {
    @Override
    public void addProjectEntity(ProjectEntity projectEntity) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            entityManager.persist(projectEntity);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        entityManager.close();
    }

    @Override
    public void removeProjectEntity(ProjectEntity projectEntity) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            entityManager.refresh(projectEntity);
            entityManager.remove(projectEntity);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        entityManager.close();
    }


    @Override
    public void updateProjectEntity(ProjectEntity projectEntity) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.unwrap(Session.class).merge(projectEntity);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        entityManager.close();
    }

    @Override
    public List<ProjectEntity> getAllProjectEntities() {
        List <ProjectEntity> projectEntities = new ArrayList<>();
        String query = "db.ProjectEntity.find({})";
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        projectEntities = entityManager.createNativeQuery(query, ProjectEntity.class).getResultList();
        entityManager.close();

        return projectEntities;
    }

    @Override
    public ProjectEntity getProjectById(String id) {
        String query = "db.ProjectEntity.find({'_id' : ObjectId(\""+ id +"\")})";
        ProjectEntity projectEntity;
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        projectEntity = (ProjectEntity) entityManager.createNativeQuery(query, ProjectEntity.class).getSingleResult();
        entityManager.close();

        return projectEntity;
    }

}
