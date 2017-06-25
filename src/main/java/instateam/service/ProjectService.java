package instateam.service;

import org.springframework.context.annotation.PropertySource;

import java.util.List;
import instateam.model.Project;

/**
 * Created by mark on 6/3/2017.
 */
public interface ProjectService {
  List<Project> findAll();
  Project findById(Long id);
  void save(Project project);
  void delete(Project project);
}
