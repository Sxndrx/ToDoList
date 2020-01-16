package toDoApp.Utils;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import toDoApp.Utils.exceptions.EmptyDateException;
import toDoApp.Utils.exceptions.EmptyTitleException;

import java.time.LocalDate;

public class Utils {
    public static void validateTitle(JFXTextField title) throws EmptyTitleException {
        if (title.getText().isEmpty()) {
            throw new EmptyTitleException();
        }
    }

    public static void setWarningOnTitle(JFXTextField text){
        text.setPromptText("Title cannot be empty!");
        text.setStyle("-fx-prompt-text-fill: #c4001d;");
    }

    public static void validateDate(JFXDatePicker datePicker) throws EmptyDateException {
        if(datePicker.getValue()==null){
            throw new EmptyDateException();
        }
    }

    public static void setWarningOnDatePicker(JFXDatePicker datePicker){
        datePicker.setPromptText("Choose correct due date!");
        datePicker.setStyle("-fx-prompt-text-fill: #c4001d;");
    }

    public static boolean isPast(LocalDate localDate){
        LocalDate today = LocalDate.now();
        if(today.isAfter(localDate)){
            return true;
        }else{
            return false;
        }
    }

}
