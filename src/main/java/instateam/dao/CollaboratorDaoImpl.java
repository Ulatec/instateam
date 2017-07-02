package instateam.dao;

import java.util.List;
import instateam.model.Collaborator;
import org.springframework.stereotype.Repository;

/**
 * Created by mark on 6/6/2017.
 */
@Repository
public class CollaboratorDaoImpl implements CollaboratorDao{

  @Override
  public List<Collaborator> findAll() {
    return null;
  }

  @Override
  public Collaborator findById(Long id) {
    return null;
  }

  @Override
  public void save(Collaborator collaborator) {

  }

  @Override
  public void delete(Collaborator collaborator) {

  }
}
