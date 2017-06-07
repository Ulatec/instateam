package instateam.service;



import java.util.List;

import instateam.dao.ProjectDao;
import instateam.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mark on 6/3/2017.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
  @Autowired
  private ProjectDao projectDao;
  @Override
  public List<Project> findAll() {
    return projectDao.findAll();
  }

  @Override
  public void save(Project project) {
    projectDao.save(project);
  }

  @Override
  public void delete(Project project) {
    projectDao.delete(project);
  }
}
