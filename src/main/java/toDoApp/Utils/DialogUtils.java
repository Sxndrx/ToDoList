package toDoApp.Utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class DialogUtils {

    public static void showSelectProject(AnchorPane pane) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Error, No selection"));
        content.setBody(new Text("Select project"));
        StackPane stackPane = new StackPane();
        stackPane.setPrefWidth(450.0);
        stackPane.setPrefHeight(510.0);
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Okay");
        pane.getChildren().add(stackPane);
        button.setOnAction(event -> {
            dialog.close();
            pane.getChildren().remove(stackPane);
        });
        content.setActions(button);
        dialog.show();
    }
}