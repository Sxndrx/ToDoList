package toDoApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import toDoApp.Utils.HibernateUtil;

public class App extends Application {


    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("TO DO LIST");
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        stage.setScene(new Scene(root));
//        Image applicationIcon = new Image(getClass().getResourceAsStream("/icons/icons8-edit-48.png"));
//        stage.getIcons().add(applicationIcon);
        stage.show();
    }
}
