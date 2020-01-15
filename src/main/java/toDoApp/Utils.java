package toDoApp;

import com.jfoenix.controls.JFXTextField;
import toDoApp.exceptions.EmptyTitleException;

public class Utils {
    public static void validateInput(JFXTextField title) throws EmptyTitleException {
        if (title.getText() == null) {
            throw new EmptyTitleException();
        }
    }

}
