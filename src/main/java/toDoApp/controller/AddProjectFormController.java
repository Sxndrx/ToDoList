package toDoApp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import toDoApp.controller.exceptions.EmptyTitleException;
import toDoApp.model.project.Project;
import toDoApp.model.project.ProjectRepo;

public class AddProjectFormController {

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXToggleButton priorityButton;

    private ObservableList<Project> projects;

    @FXML
    void initialize() {
        saveButton.setOnMouseClicked(event -> saveProject());
    }

    private void saveProject() {
        try {
            validateInput();
            Project project = new Project();
            if (title.getText() != null) {
                project.setTitle(title.getText());
            }
            if (description.getText() != null) {
                project.setDescription(description.getText());
            }

            project.setPriority(priorityButton.isSelected());
            projects.add(project);
            ProjectRepo.addProject(project);


            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        } catch (EmptyTitleException e) {
            title.setPromptText(e.getMessage());
            title.setStyle("-fx-prompt-text-fill: #c4001d;");
        }


    }

    public void setProjects(ObservableList<Project> projects) {
        this.projects = projects;
    }

    private void validateInput() throws EmptyTitleException {
        if (title.getText() == null) {
            throw new EmptyTitleException();
        }
    }

}
