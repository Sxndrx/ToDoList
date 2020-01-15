package toDoApp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import toDoApp.model.project.Project;
import toDoApp.model.project.ProjectRepo;

import java.io.IOException;


public class ProjectListViewCell extends ListCell<Project> {

    @FXML
    private Label nameLabel;

    @FXML
    private ImageView deleteBtn;

    @FXML
    private ImageView editBtn;

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
                this.setStyle("-fx-background-color: #efdca5");
            }else{
                this.setStyle("-fx-background-color: #efefef");
            }

            setText(null);
            setGraphic(anchorPane);

        }

    }

    private void setEventHandlers(){
        deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED , event -> deleteProject());
    }

    private void deleteProject(){
        Project project = getListView().getSelectionModel().getSelectedItem();
        System.out.println(project.getTitle());
        getListView().getItems().remove(project);
        ProjectRepo.removeProject(project);
    }

}
