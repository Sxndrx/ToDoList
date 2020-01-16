package toDoApp.controller.ListViews;

import com.jfoenix.controls.JFXListView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import toDoApp.controller.Forms.ProjectFormController;
import toDoApp.controller.ListViews.ListCells.ProjectListViewCell;
import toDoApp.model.models.Project;
import toDoApp.model.repo.ProjectRepo;

import java.io.IOException;

public class ProjectListViewController {

    ObservableList<Project> projectObservableList;
    @FXML
    private JFXListView<Project> projectsListView;
    @FXML
    private ImageView imgAdd;

    public ProjectListViewController() {
        projectObservableList = ProjectRepo.getAllProjects();
    }

    @FXML
    void initialize() {
        projectsListView.setItems(projectObservableList);
        projectsListView.setCellFactory(projectListView -> new ProjectListViewCell());
        imgAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> openAddProjectWindow());
    }


    private void openAddProjectWindow() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Forms/ProjectForm.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            ProjectFormController controller = fxmlLoader.getController();
            controller.setProjects(projectObservableList);
            controller.setFormMode(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("TO DO LIST");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public JFXListView<Project> getProjectsListView() {
        return projectsListView;
    }
}
