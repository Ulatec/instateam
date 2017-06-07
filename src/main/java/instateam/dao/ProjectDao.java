package instateam.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import instateam.model.Project;

/**
 * Created by mark on 5/31/2017.
 */
public interface ProjectDao {
  List<Project> findAll();
  Project findById(Long id);
  void save(Project project);
  void delete(Project project);
}
