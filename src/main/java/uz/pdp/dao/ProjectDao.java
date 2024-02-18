package uz.pdp.dao;


import uz.pdp.model.ProjectEntity;

public interface ProjectDao extends CrudDao<ProjectEntity, Long> {
    ProjectEntity findByProjectName(String projectName);
}
