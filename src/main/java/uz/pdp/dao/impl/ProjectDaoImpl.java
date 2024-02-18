package uz.pdp.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import uz.pdp.dao.ProjectDao;
import uz.pdp.model.ProjectEntity;
import uz.pdp.rowmapper.UserRowMapper;

@Repository
@RequiredArgsConstructor
public class ProjectDaoImpl implements ProjectDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    @Override
    public ProjectEntity save(ProjectEntity projectEntity) {


        jdbcTemplate.update("""   
                         INSERT INTO project(project_name, project_developer , file_name) 
                        VALUES (?, (select id from users where email = ?), ?)
                        """,
                projectEntity.getProjectName(),
                projectEntity.getProjectDeveloper(),
                projectEntity.getOriginalName()
                );
        return projectEntity;

    }

    @Override
    public ProjectEntity getById(Long d) {
        return null;
    }

    @Override
    public ProjectEntity update(ProjectEntity projectEntity) {
        return null;
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }

    @Override
    public ProjectEntity findByProjectName(String projectName) {
        return null;
    }
}
