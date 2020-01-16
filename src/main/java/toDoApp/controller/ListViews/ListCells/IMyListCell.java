package toDoApp.controller.ListViews.ListCells;

public interface IMyListCell {
    void setEventHandlers();
    void delete();
    void showMore();
    void setCellGraphic(Object item);
}