package toDoApp.controller;

import com.jfoenix.controls.JFXListView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import toDoApp.model.project.Project;
import toDoApp.model.project.ProjectRepo;

import java.io.IOException;

public class ProjectListViewController {

    @FXML
    private JFXListView<Project> projectsListView;

    @FXML
    private ImageView imgAdd;

    ObservableList<Project> projectObservableList;

    public ProjectListViewController() {
       projectObservableList = ProjectRepo.getAllProjects();
    }

    @FXML
    void initialize() {
       projectsListView.setItems(projectObservableList);
       projectsListView.setCellFactory(projectListView -> new ProjectListViewCell());
       imgAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> openAddProjectWindow());
    }



    private void openAddProjectWindow(){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ProjectForm.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            ProjectFormController controller = fxmlLoader.getController();
            controller.setProjects(projectObservableList);
            controller.setAddNew(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("TO DO LIST");
        stage.show();
    }

    public JFXListView<Project> getProjectsListView() {
        return projectsListView;
    }
}
