package toDoApp.Utils.exceptions;

public class EmptyDateException extends Exception {
    String message = "Choose due date";

    @Override
    public String getMessage() {
        return message;
    }
}
