package toDoApp.Utils.exceptions;

import javax.management.monitor.StringMonitor;

public class WrongNotifacationException extends Exception {
    String message = "Notification set in past";

    @Override
    public String getMessage() {
        return message;
    }
}
