package toDoApp.controller.ListViews.ListCells;

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
import toDoApp.controller.Forms.ProjectFormController;
import toDoApp.model.models.Project;
import toDoApp.model.repo.ProjectRepo;

import java.io.IOException;


public class ProjectListViewCell extends ListCell<Project> implements IMyListCell {

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
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/ListViews/ListCells/ProjectCell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                    setEventHandlers();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            setCellGraphic(item);
            setText(null);
            setGraphic(anchorPane);

        }

    }

    @Override
    public void setEventHandlers() {
        deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> delete());
        showMoreBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showMore());
    }

    @Override
    public void delete() {
        Project project = getListView().getSelectionModel().getSelectedItem();
        getListView().getItems().remove(project);
        ProjectRepo.removeProject(project);
    }

    @Override
    public void showMore() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Forms/ProjectForm.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            ProjectFormController controller = fxmlLoader.getController();
            controller.setProjects(getListView().getItems());
            controller.setSelectedProject(getListView().getSelectionModel().getSelectedItem());
            controller.setFormMode(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("TO DO LIST");
        stage.show();
    }

    @Override
    public void setCellGraphic(Object item) {
        Project project = (Project) item;
        if (nameLabel == null) {
            nameLabel = new Label();
        }
        nameLabel.setText(project.getTitle());
        if (project.getPriority()) {
            anchorPane.setStyle("-fx-background-color: #efdca5");
        } else {
            anchorPane.setStyle("-fx-background-color: #efefef");
        }

    }
}
