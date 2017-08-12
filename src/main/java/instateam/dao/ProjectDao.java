package instateam.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import instateam.model.Project;

public interface ProjectDao {
  List<Project> findAll();
  Project findById(Long id);
  void save(Project project);
  void delete(Project project);
}
