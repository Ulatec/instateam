package instateam.dao;

import java.util.List;
import instateam.model.Collaborator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CollaboratorDaoImpl implements CollaboratorDao{
  @Autowired
  private SessionFactory sessionFactory;
  @Override
  @SuppressWarnings("unchecked")
  public List<Collaborator> findAll() {
    Session session = sessionFactory.openSession();
    List<Collaborator> collaborators = session.createCriteria(Collaborator.class).list();
    session.close();
    return collaborators;
  }

  @Override
  public Collaborator findById(Long id) {
    Session session = sessionFactory.openSession();
    Collaborator collaborator = session.get(Collaborator.class, id);
    session.close();
    return collaborator;
  }

  @Override
  public void save(Collaborator collaborator) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.saveOrUpdate(collaborator);
    session.getTransaction().commit();
    session.close();
  }

  @Override
  public void delete(Collaborator collaborator) {

  }
}
