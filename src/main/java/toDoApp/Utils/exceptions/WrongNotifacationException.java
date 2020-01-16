package toDoApp.Utils.exceptions;

public class WrongNotifacationException extends Exception {
    String message = "Notification set in past";

    @Override
    public String getMessage() {
        return message;
    }
}
