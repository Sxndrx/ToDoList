package toDoApp.controller;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import toDoApp.controller.ListViews.ProjectListViewController;
import toDoApp.controller.ListViews.TaskListViewController;
import toDoApp.model.project.Project;

import java.util.List;

public class MainController {
    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private TaskListViewController taskListViewController;

    @FXML
    private ProjectListViewController projectListViewController;

    private JFXListView<Project> projectList;
    private FXMLLoader fxmlLoader;

    @FXML
    void initialize() {
        projectList = projectListViewController.getProjectsListView();
        projectList.getSelectionModel().selectedItemProperty().addListener(event -> {
            taskListViewController.updateTaskListFromProject(projectList.getSelectionModel().getSelectedItem());
        });
        projectList.getSelectionModel().selectFirst();
    }

    private void checkToNotificate(){
    }


}
