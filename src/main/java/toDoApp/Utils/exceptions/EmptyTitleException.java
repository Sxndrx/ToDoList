package toDoApp.Utils.exceptions;

public class EmptyTitleException extends Exception{

    String message = "Title cannot be empty";

    @Override
    public String getMessage() {
        return message;
    }

}
