package instateam.service;

import instateam.model.Collaborator;
import instateam.model.Project;

import java.util.List;

public interface CollaboratorService {
    List<Collaborator> findAll();
    Collaborator findById(Long id);
    void save(Collaborator collaborator);
    void delete(Collaborator collaborator);
}
