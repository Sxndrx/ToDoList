package toDoApp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import toDoApp.model.project.Project;
import toDoApp.model.project.ProjectRepo;

import java.io.IOException;


public class ProjectListViewCell extends ListCell<Project> {

    @FXML
    private Label nameLabel;

    @FXML
    private ImageView deleteBtn;

    @FXML
    private ImageView showMoreBtn;

    @FXML
    private AnchorPane anchorPane;


    private FXMLLoader fxmlLoader;

    public ProjectListViewCell() {
    }

    @Override
    protected void updateItem(Project item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {

            setText(null);
            setGraphic(null);

        } else {
            if(fxmlLoader==null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/ProjectCell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                    setEventHandlers();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(nameLabel ==null){
                nameLabel = new Label();
            }

            nameLabel.setText(item.getTitle());
            if(item.getPriority()){
                anchorPane.setStyle("-fx-background-color: #efdca5");
            }else{
                anchorPane.setStyle("-fx-background-color: #efefef");
            }

            setText(null);
            setGraphic(anchorPane);

        }

    }

    private void setEventHandlers(){
        deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED , event -> deleteProject());
        showMoreBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showMore());
    }

    private void deleteProject(){
        Project project = getListView().getSelectionModel().getSelectedItem();
        System.out.println(project.getTitle());
        getListView().getItems().remove(project);
        ProjectRepo.removeProject(project);
    }

    private void showMore(){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ProjectForm.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            ProjectFormController controller = fxmlLoader.getController();
            controller.setProjects(getListView().getItems());
            controller.setSelectedProject(getListView().getSelectionModel().getSelectedItem());
            controller.setAddNew(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("TO DO LIST");
        stage.show();
    }

}
