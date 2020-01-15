package toDoApp.controller;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane mainAnchor;

    private FXMLLoader fxmlLoader;

    @FXML
    void initialize() {
        AnchorPane projectViewAnchor = null;
        AnchorPane taskViewAnchor = null;
        TaskListViewController taskListViewController;
        ProjectListViewController projectListViewController;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/ProjectListView.fxml"));
            projectListViewController = fxmlLoader.getController();
            projectViewAnchor = fxmlLoader.load();
            mainAnchor.setLeftAnchor(projectViewAnchor, 0.0);
            mainAnchor.setTopAnchor(projectViewAnchor, 0.0);
            mainAnchor.getChildren().add(projectViewAnchor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fxmlLoader = new FXMLLoader(getClass().getResource("/view/TaskListView.fxml"));
        taskListViewController = fxmlLoader.getController();
        try {
            taskViewAnchor = fxmlLoader.load();
            mainAnchor.setLeftAnchor(taskViewAnchor, 300.0);
            mainAnchor.setTopAnchor(projectViewAnchor, 0.0);
            mainAnchor.getChildren().add(taskViewAnchor);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
