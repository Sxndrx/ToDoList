package toDoApp;

import org.bson.types.ObjectId;
import toDoApp.projectModel.IProjectDao;
import toDoApp.projectModel.Project;
import toDoApp.projectModel.ProjectDao;
import toDoApp.taskModel.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class App {

    public static void main(String[] args) {
        List<Project> projects = new ArrayList<>();
        ProjectDao projectDao = new ProjectDao();
        projects = projectDao.getAllProjects();
        for(Project project : projects){
            System.out.println(project.toString());
        }


        HibernateUtil.closeEntityManagerFactory();

    }
}
