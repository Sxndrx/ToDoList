package toDoApp.model.repo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import toDoApp.database.dao.IProjectDao;
import toDoApp.database.dao.ProjectDao;
import toDoApp.database.entities.ProjectEntity;
import toDoApp.model.models.Project;

import java.util.LinkedList;
import java.util.List;

public class ProjectRepo {
    private static IProjectDao projectDao = new ProjectDao();

    public static void addProject(Project project) {
        ProjectEntity projectEntity = project.toProjectEntity();
        projectDao.addProjectEntity(projectEntity);
        project.setId(projectEntity.getId());
    }

    public static void updateProject(Project project) {
        ProjectEntity projectEntity = project.toProjectEntity();
        projectDao.updateProjectEntity(projectEntity);
    }

    public static void removeProject(Project project) {
        TaskRepo.removeTasksFromProject(project.getId());
        ProjectEntity projectEntity = project.toProjectEntity();
        projectDao.removeProjectEntity(projectEntity);
    }

    public static ObservableList<Project> getAllProjects() {
        List<ProjectEntity> projectEntities;
        projectEntities = projectDao.getAllProjectEntities();
        List<Project> projects = new LinkedList<>();
        for (ProjectEntity projectEntity : projectEntities) {
            projects.add(new Project(projectEntity));
        }
        return FXCollections.observableList(projects);
    }
}
