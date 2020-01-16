package toDoApp.controller.Forms;

import com.jfoenix.controls.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import toDoApp.Utils.Utils;
import toDoApp.Utils.exceptions.EmptyDateException;
import toDoApp.Utils.exceptions.EmptyTitleException;
import toDoApp.controller.ListViews.ListCells.IFormController;
import toDoApp.model.project.Project;
import toDoApp.model.task.Task;
import toDoApp.model.task.TaskRepo;


public class TaskFormController implements IFormController {
    @FXML
    private Label taskName;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXToggleButton priorityButton;
    @FXML
    private JFXToggleButton notifyButton;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton editBtn;

    private Task selectedTask;
    private Project project;
    private Task parentTask;
    private boolean addNew = true;
    private boolean subTask = false;
    private ObservableList<Task> tasks;

    @FXML
    void initialize(){
        setEventHandlers();
    }

    @Override
    public void setEventHandlers() {
        saveBtn.setOnMouseClicked(event -> {
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
            Utils.validateDate(datePicker);
            Task task = new Task();
            setTask(task);
            tasks.add(task);
            TaskRepo.addTask(task);
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        } catch (EmptyTitleException e) {
            Utils.setWarningOnTitle(title);
        } catch (EmptyDateException e) {
            Utils.setWarningOnDatePicker(datePicker);
        }
    }


    @Override
    public void update() {
        try {
            Utils.validateTitle(title);
            Utils.validateDate(datePicker);
            setTask(selectedTask);
            TaskRepo.updateTask(selectedTask);
            tasks.set(tasks.indexOf(selectedTask),selectedTask);
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        } catch (EmptyTitleException e) {
            Utils.setWarningOnTitle(title);
        } catch (EmptyDateException e) {
            Utils.setWarningOnDatePicker(datePicker);
        }
    }

    @Override
    public void showMore() {
        setWindow();
        setDisabled(true);
    }

    @Override
    public void setWindow() {
        editBtn.setVisible(true);
        taskName.setText(selectedTask.getTitle());
        title.setText(selectedTask.getTitle());
        description.setText(selectedTask.getDescription());
        datePicker.setValue(selectedTask.getDate());
        priorityButton.setSelected(selectedTask.getPriority());
        notifyButton.setDisable(selectedTask.getNotify());
    }


    @Override
    public void setDisabled(boolean disabled) {
        title.setDisable(disabled);
        description.setDisable(disabled);
        datePicker.setDisable(disabled);
        priorityButton.setDisable(disabled);
        notifyButton.setDisable(disabled);
        saveBtn.setDisable(disabled);
    }


    private void setTask(Task task){
        task.setTitle(title.getText());
        if (description.getText() != null) {
            task.setDescription(description.getText());
        }
        task.setPriority(priorityButton.isSelected());
        task.setNotify(notifyButton.isSelected());
        task.setDate(datePicker.getValue());
        if(addNew){
            if(parentTask!=null){
                task.setParentTask(parentTask);
            }
                task.setProject(project);
        }

    }

    public void setTasks(ObservableList<Task> tasks) {
        this.tasks = tasks;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public void setFormMode(boolean addNew) {
        this.addNew = addNew;
        if(!addNew){
            showMore();
        }
    }
}
