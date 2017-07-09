package instateam.service;

import instateam.dao.CollaboratorDao;
import instateam.model.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mark on 6/25/2017.
 */
@Service
public class CollaboratorServiceImpl implements CollaboratorService{
    @Autowired
    private CollaboratorDao collaboratorDao;
    @Override
    public List<Collaborator> findAll() {
        return collaboratorDao.findAll();
    }

    @Override
    public Collaborator findById(Long id) {
        return collaboratorDao.findById(id);
    }

    @Override
    public void save(Collaborator collaborator) {
        collaboratorDao.save(collaborator);
    }

    @Override
    public void delete(Collaborator collaborator) {

    }
}
