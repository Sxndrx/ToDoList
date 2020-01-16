package toDoApp.controller.ListViews;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import toDoApp.Utils.DialogUtils;
import toDoApp.controller.Forms.TaskFormController;
import toDoApp.controller.ListViews.ListCells.TaskListViewCell;
import toDoApp.model.models.Project;
import toDoApp.model.models.Task;
import toDoApp.model.repo.TaskRepo;

import java.io.IOException;
import java.util.List;

public class TaskListViewController {
    ObservableList<Task> taskObservableList;
    @FXML
    private ImageView addTaskBtn;
    @FXML
    private JFXListView<Task> taskListView;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label parentName;
    private Project project;
    private Task parentTask;


    public TaskListViewController() {
        taskObservableList = FXCollections.observableArrayList();
    }


    @FXML
    void initialize() {
        setEventHandlers();
        taskListView.setItems(taskObservableList);
        taskListView.setCellFactory(taskList -> new TaskListViewCell());
    }

    private void setTaskObservableList() {
        List<Task> list;
        if (parentTask == null) {
            list = TaskRepo.getTasksFromProject(project.getId());
        } else {
            list = TaskRepo.getSubTasksFromParent(parentTask);
        }
        if (list.isEmpty()) {
            taskObservableList.clear();
        } else {
            taskObservableList.clear();
            taskObservableList.addAll(list);
        }
    }

    public void updateTaskListFromProject(Project project) {
        this.project = project;
        if (project == null) {
            setHeader("");
        } else {
            setHeader(project.getTitle());
            setTaskObservableList();
        }
    }

    public void setHeader(String text) {
        parentName.setText(text);
    }

    private void setEventHandlers() {
        addTaskBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> addTask());
    }

    private void addTask() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Forms/TaskForm.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            TaskFormController taskFormController = fxmlLoader.getController();
            taskFormController.setProject(project);
            if (parentTask != null) {
                taskFormController.setParentTask(parentTask);
            }
            taskFormController.setTasks(taskObservableList);

            stage.setScene(new Scene(root));
            stage.setTitle("TO DO LIST");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            DialogUtils.showSelectProject(pane);
        }
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
        setTaskObservableList();
        setHeader(parentTask.getTitle());
        project = parentTask.getProject();
    }
}
