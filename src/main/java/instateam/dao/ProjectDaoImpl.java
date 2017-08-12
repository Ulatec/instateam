package instateam.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;
import instateam.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public class ProjectDaoImpl implements ProjectDao{

  @Autowired
  private SessionFactory sessionFactory;
  @Override
  @SuppressWarnings("unchecked")
  public List<Project> findAll() {
    Session session = sessionFactory.openSession();
    List<Project> categories = session.createCriteria(Project.class).list();
    session.close();
    return categories;
  }

  @Override
  public Project findById(Long id) {
    Session session = sessionFactory.openSession();
    Project project = session.get(Project.class, id);
    Hibernate.initialize(project.getCollaborators());
    Hibernate.initialize(project.getRolesNeeded());
    session.close();
    return project;
  }

  @Override
  public void save(Project project) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.saveOrUpdate(project);
    session.getTransaction().commit();
    session.close();
  }

  @Override
  public void delete(Project project) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.delete(project);
    session.getTransaction().commit();
    session.close();
  }
}
