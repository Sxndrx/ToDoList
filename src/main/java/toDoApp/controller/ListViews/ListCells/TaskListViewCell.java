package toDoApp.controller.ListViews.ListCells;

import com.jfoenix.controls.JFXCheckBox;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import toDoApp.Utils.Utils;
import toDoApp.controller.Forms.TaskFormController;
import toDoApp.controller.ListViews.TaskListViewController;
import toDoApp.model.models.Task;
import toDoApp.model.repo.TaskRepo;

import java.io.IOException;

public class TaskListViewCell extends ListCell<Task> implements IMyListCell {
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView deleteBtn;
    @FXML
    private ImageView showMoreBtn;
    @FXML
    private Label dueDateLabel;
    @FXML
    private JFXCheckBox doneBtn;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView showSubTasksBtn;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Task item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/ListViews/ListCells/TaskCell.fxml"));
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
        showMoreBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showMore());
        deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> delete());
        showSubTasksBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showSubTasks());
        doneBtn.setOnMouseClicked(event -> makeDone());
    }

    private void makeDone() {
        Task task = getItem();
        task.setDone(!task.isDone());
        TaskRepo.updateTask(task);
        updateItem(task, false);

    }

    private void showSubTasks() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ListViews/TaskListView.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            TaskListViewController controller = fxmlLoader.getController();
            controller.setParentTask(getItem());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("TO DO LIST");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @Override
    public void delete() {
        Task task = getListView().getSelectionModel().getSelectedItem();
        TaskRepo.removeTask(task);
        getListView().getItems().remove(task);
    }

    @Override
    public void showMore() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Forms/TaskForm.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            TaskFormController controller = fxmlLoader.getController();
            controller.setTasks(getListView().getItems());
            controller.setSelectedTask(getListView().getSelectionModel().getSelectedItem());
            controller.setAddNewFormMode(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("TO DO LIST");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @Override
    public void setCellGraphic(Object item) {
        Task task = (Task) item;
        nameLabel.setText(task.getTitle());
        if (task.dateProperty() != null) {
            dueDateLabel.setText(task.getDate().toString());
        } else {
            dueDateLabel.setText("without time limit");
        }
        doneBtn.setSelected(task.isDone());
        if (task.isDone()) {
            anchorPane.setStyle("-fx-background-color: #bfefc8");
        } else if (Utils.isPast(task.getDate())) {
            anchorPane.setStyle("-fx-background-color: #efbaba");
        } else if (task.isPriority()) {
            anchorPane.setStyle("-fx-background-color: #efdca5");
        } else {
            anchorPane.setStyle("-fx-background-color: #efefef");
        }
    }

}
