package toDoApp.model.project;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import toDoApp.database.project.IProjectDao;
import toDoApp.database.project.ProjectDao;
import toDoApp.database.project.ProjectEntity;
import toDoApp.model.task.TaskRepo;

import java.util.LinkedList;
import java.util.List;

public class ProjectRepo {
    private static IProjectDao projectDao = new ProjectDao();

    public static void addProject(Project project){
        ProjectEntity projectEntity = project.toProjectEntity();
        Platform.runLater(() -> {
            projectDao.addProjectEntity(projectEntity);
        });
    }

    public static void updateProject(Project project){
        ProjectEntity projectEntity = project.toProjectEntity();
        Platform.runLater(()->{
            projectDao.updateProjectEntity(projectEntity);
        });
    }
    public static void removeProject(Project project){
        TaskRepo.removeTasksFromProject(project.getId());
        ProjectEntity projectEntity = project.toProjectEntity();
        Platform.runLater(()->{
            projectDao.removeProjectEntity(projectEntity);
        });
    }
    public static ObservableList<Project> getAllProjects(){
        List<ProjectEntity> projectEntities;
        projectEntities = projectDao.getAllProjectEntities();
        List<Project> projects = new LinkedList<>();
        for(ProjectEntity projectEntity : projectEntities){
            projects.add(new Project(projectEntity));
        }
        return FXCollections.observableList(projects);
    }
}
