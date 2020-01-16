package toDoApp.controller.ListViews.ListCells;

public interface IFormController {
    void setEventHandlers();
    void save();
    void update();
    void showMore();
    void setWindow();
    void setDisabled(boolean disabled);
}
