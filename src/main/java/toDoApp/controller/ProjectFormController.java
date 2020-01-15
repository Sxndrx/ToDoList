package toDoApp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import toDoApp.Utils;
import toDoApp.exceptions.EmptyTitleException;
import toDoApp.model.project.Project;
import toDoApp.model.project.ProjectRepo;

public class ProjectFormController {

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXToggleButton priorityButton;
    @FXML
    private Label projectName;
    @FXML
    private JFXButton editBtn;


    private Project selectedProject;
    private boolean addNew = true;
    private ObservableList<Project> projects;

    @FXML
    void initialize() {
        setEventHandlers();
    }

    private void setEventHandlers(){
        saveButton.setOnMouseClicked(event -> {
            if(addNew){
                saveProject();
            }else{
                update();
            }
        });
        editBtn.setOnMouseClicked(event -> setDisabled(false));
    }

    private void saveProject() {
        try {
            Utils.validateInput(title);
            Project project = new Project();
            setProject(project);
            projects.add(project);
            ProjectRepo.addProject(project);
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        } catch (EmptyTitleException e) {
            title.setPromptText(e.getMessage());
            title.setStyle("-fx-prompt-text-fill: #c4001d;");
        }
    }

    private void update(){
        try {
            Utils.validateInput(title);
            setProject(selectedProject);
            projects.set(projects.indexOf(selectedProject), selectedProject);
            ProjectRepo.updateProject(selectedProject);
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        } catch (EmptyTitleException e) {
            title.setPromptText(e.getMessage());
            title.setStyle("-fx-prompt-text-fill: #c4001d;");
        }
    }

    private void setProject(Project project){
        if (title.getText() != null) {
            project.setTitle(title.getText());
        }
        if (description.getText() != null) {
            project.setDescription(description.getText());
        }
        project.setPriority(priorityButton.isSelected());
    }

    public void setProjects(ObservableList<Project> projects) {
        this.projects = projects;
    }

    private void showMore(){
        setWindow();
        setDisabled(true);
    }

    private void setWindow(){
        projectName.setText(selectedProject.getTitle());
        title.setText(selectedProject.getTitle());
        description.setText(selectedProject.getDescription());
        priorityButton.setSelected(selectedProject.getPriority());
        editBtn.setVisible(true);
    }

    private void setDisabled(boolean disabled){
        title.setDisable(disabled);
        description.setDisable(disabled);
        priorityButton.setDisable(disabled);
        saveButton.setDisable(disabled);
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public void setAddNew(boolean addNew) {
        this.addNew = addNew;
        if(!addNew){
      //      setSelectedProject(projects.get(0));
            showMore();
        }
    }
}
