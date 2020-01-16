package toDoApp.controller.Forms;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import toDoApp.Utils.Utils;
import toDoApp.Utils.exceptions.EmptyTitleException;
import toDoApp.controller.ListViews.ListCells.IFormController;
import toDoApp.model.project.Project;
import toDoApp.model.project.ProjectRepo;

public class ProjectFormController implements IFormController {

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

    @Override
    public void setEventHandlers(){
        saveButton.setOnMouseClicked(event -> {
            if(addNew){
                save();
            }else{
                update();
            }
        });
        editBtn.setOnMouseClicked(event -> setDisabled(false));
    }

    @Override
    public void save() {
        try {
            Utils.validateTitle(title);
            Project project = new Project();
            setProject(project);
                ProjectRepo.addProject(project);
                projects.add(project);
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        } catch (EmptyTitleException e) {
            Utils.setWarningOnTitle(title);
        }
    }

    @Override
    public void update(){
        try {
            Utils.validateTitle(title);
            setProject(selectedProject);
            projects.set(projects.indexOf(selectedProject), selectedProject);
            ProjectRepo.updateProject(selectedProject);
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        } catch (EmptyTitleException e) {
            Utils.setWarningOnTitle(title);
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

    @Override
    public void showMore(){
        setWindow();
        setDisabled(true);
    }

    @Override
    public void setWindow(){
        projectName.setText(selectedProject.getTitle());
        title.setText(selectedProject.getTitle());
        description.setText(selectedProject.getDescription());
        priorityButton.setSelected(selectedProject.getPriority());
        editBtn.setVisible(true);
    }

    @Override
    public void setDisabled(boolean disabled){
        title.setDisable(disabled);
        description.setDisable(disabled);
        priorityButton.setDisable(disabled);
        saveButton.setDisable(disabled);
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public void setFormMode(boolean addNew) {
        this.addNew = addNew;
        if(!addNew){
            showMore();
        }
    }
}
