package toDoApp.projectModel;

import org.hibernate.Session;
import toDoApp.HibernateUtil;
import toDoApp.taskModel.Task;
import org.bson.types.ObjectId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao implements IProjectDao {
    @Override
    public void addProject(Project project) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(project);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void removeProject(Project project) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.refresh(project);
        entityManager.remove(project);
        entityManager.flush();
        entityManager.close();
    }

    @Override
    public List<Task> getTasks(ObjectId id) {
        return null;
    }


    @Override
    public void updateProject(Project project) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.unwrap(Session.class).merge(project);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Project> getAllProjects() {
        List <Project> projects = new ArrayList<>();
        String query = "db.Project.find({})";
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        projects = entityManager.createNativeQuery(query, Project.class).getResultList();
        entityManager.close();

        return projects;
    }
}
