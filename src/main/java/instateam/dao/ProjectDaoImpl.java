package instateam.dao;

import java.util.List;
import instateam.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by mark on 5/31/2017.
 */
public class ProjectDaoImpl implements ProjectDao{
  @Autowired
  private SessionFactory sessionFactory;
  @Override
  public List<Project> findAll() {
    Session session = sessionFactory.openSession();

    session.close();
    return null;
  }

  @Override
  public Project findById(Long id) {
    Session session = sessionFactory.openSession();
    return null;
  }

  @Override
  public void save(Project project) {
    sessionFactory.openSession();
  }

  @Override
  public void delete(Project project) {
    Session session = sessionFactory.openSession();
  }
}
