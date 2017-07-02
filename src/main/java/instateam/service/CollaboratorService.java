package instateam.service;

import instateam.model.Collaborator;
import instateam.model.Project;

import java.util.List;

/**
 * Created by mark on 6/25/2017.
 */
public interface CollaboratorService {
    List<Collaborator> findAll();
    Collaborator findById(Long id);
    void save(Collaborator collaborator);
    void delete(Collaborator collaborator);
}
