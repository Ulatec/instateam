package instateam.dao;

import java.util.List;
import instateam.model.Collaborator;

public interface CollaboratorDao {
  List<Collaborator> findAll();
  Collaborator findById(Long id);
  void save(Collaborator collaborator);
  void delete(Collaborator collaborator);
}
