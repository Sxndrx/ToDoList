package toDoApp.database.project;


import java.util.List;

public interface IProjectDao {
    void addProjectEntity(ProjectEntity projectEntity);
    void removeProjectEntity(ProjectEntity projectEntity);
    void updateProjectEntity(ProjectEntity projectEntity);
    List<ProjectEntity> getAllProjectEntities();
    ProjectEntity getProjectById(String id);
}
