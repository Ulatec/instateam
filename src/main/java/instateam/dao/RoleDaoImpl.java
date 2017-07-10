package instateam.dao;


import instateam.model.Project;
import instateam.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mark on 7/9/2017.
 */
public class RoleDaoImpl implements RoleDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    @SuppressWarnings("unchecked")
    public List<Project> findAll() {
        Session session = sessionFactory.openSession();
        List<Project> categories = session.createCriteria(Role.class).list();
        session.close();
        return categories;
    }

    @Override
    public Project findById(Long id) {
        Session session = sessionFactory.openSession();
        Project project = session.get(Project.class, id);
        session.close();
        return project;
    }

    @Override
    public void save(Role role) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Role role) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(role);
        session.getTransaction().commit();
        session.close();
    }
}
