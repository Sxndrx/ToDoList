package toDoApp.controller.Forms;

import toDoApp.Utils.exceptions.EmptyDateException;
import toDoApp.Utils.exceptions.EmptyTitleException;
import toDoApp.Utils.exceptions.WrongNotifacationException;

public interface IFormController {
    void setEventHandlers();
    void save();
    void addNewObject();
    void validateInput() throws EmptyTitleException, EmptyDateException, WrongNotifacationException;
    void update();
    void showMore();
    void setWindow();
    void setDisabled(boolean disabled);
}
