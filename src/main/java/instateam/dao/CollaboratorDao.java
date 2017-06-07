package instateam.dao;

import java.util.List;
import instateam.model.Collaborator;

/**
 * Created by mark on 6/6/2017.
 */
public interface CollaboratorDao {
  List<Collaborator> findAll();
  Collaborator findById(Long id);
  void save(Collaborator collaborator);
  void delete(Collaborator collaborator);
}
