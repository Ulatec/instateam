package instateam.service;

import org.springframework.context.annotation.PropertySource;

import java.util.List;
import instateam.model.Project;


public interface ProjectService {
  List<Project> findAll();
  Project findById(Long id);
  void save(Project project);
  void delete(Project project);
}
