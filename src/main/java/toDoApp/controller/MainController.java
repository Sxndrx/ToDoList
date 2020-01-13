package toDoApp.controller;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {
    @FXML
    private JFXListView<?> projectsListView;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    void initialize() {
        AnchorPane projectViewAnchor = null;
        try {
            projectViewAnchor = FXMLLoader.load(getClass().getResource("/view/ProjectsView.fxml"));
            mainAnchor.setLeftAnchor(projectViewAnchor, 0.0);
            mainAnchor.setTopAnchor(projectViewAnchor, 0.0);
            mainAnchor.getChildren().add(projectViewAnchor);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
