package toDoApp.Utils;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import net.bytebuddy.asm.Advice;
import toDoApp.Utils.exceptions.EmptyDateException;
import toDoApp.Utils.exceptions.EmptyTitleException;
import toDoApp.Utils.exceptions.WrongNotifacationException;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

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

    public static void validateNotificationDate(JFXDatePicker notDatePicker, JFXTimePicker notTimePicker)
            throws WrongNotifacationException{
        try {
            validateDate(notDatePicker);
            validateTime(notTimePicker);
            LocalDateTime localDateTime = toLocalDateTime(notDatePicker.getValue(), notTimePicker.getValue());
            if(localDateTime.isBefore(LocalDateTime.now().plusMinutes(1))) {
                throw new WrongNotifacationException();
            }

        } catch (EmptyDateException e) {
            throw new WrongNotifacationException();
        }
    }


    public static void validateTime(JFXTimePicker timePicker) throws EmptyDateException{
        if(timePicker.getValue()==null){
            throw new EmptyDateException();
        }
    }
    public static void setWarningOnTimePicker(JFXTimePicker timePicker){
        timePicker.setPromptText("Choose correct time!");
        timePicker.setStyle("-fx-prompt-text-fill: #c4001d;");
    }


    public static void setWarningOnDatePicker(JFXDatePicker datePicker){
        datePicker.setPromptText("Choose correct date!");
        datePicker.setStyle("-fx-prompt-text-fill: #c4001d;");
    }

    public static boolean isPast(LocalDateTime localDateTime){
        LocalDateTime today = LocalDateTime.now();
        if(today.isAfter(localDateTime)){
            return true;
        }else{
            return false;
        }
    }

    public static LocalDateTime toLocalDateTime(LocalDate date, LocalTime time){
        return LocalDateTime.from(date.atTime(time));
    }

    public static LocalDateTime toLocalDateTime(Date date){
        return  Instant.ofEpochMilli( date.getTime() )
                .atZone( ZoneId.systemDefault() )
                .toLocalDateTime();
    }

    public static Date toDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
