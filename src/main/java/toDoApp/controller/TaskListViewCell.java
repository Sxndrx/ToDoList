package toDoApp.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import toDoApp.model.task.Task;

import java.io.IOException;

public class TaskListViewCell extends ListCell<Task> {
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

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Task item, boolean empty) {
        super.updateItem(item, empty);

        if(empty || item==null){
            setText(null);
            setGraphic(null);
        }else{
            if(fxmlLoader==null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/TaskCell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            setText(null);
            setGraphic(anchorPane);
        }
    }
}
