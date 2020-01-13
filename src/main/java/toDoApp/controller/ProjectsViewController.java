package toDoApp.controller;

import com.jfoenix.controls.JFXListView;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import toDoApp.model.projectModel.Project;
import toDoApp.model.projectModel.ProjectDao;
import toDoApp.model.projectModel.ProjectEntity;

import java.util.List;

public class ProjectsViewController {
    ProjectDao projectDao = new ProjectDao();
    @FXML
    private JFXListView<Project> projectsListView;

    @FXML
    void initialize() {
//        List<ProjectEntity> projectEntities = projectDao.getAllProjectEntities();
//        System.out.println(projectEntities.size());
//        for (ProjectEntity projectEntity : projectEntities){
//           projectsListView.getItems().add(new Project(projectEntity));
//        }
    }
}
