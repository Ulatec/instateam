package instateam.controller;

import instateam.model.Project;
import instateam.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by mark on 5/31/2017.
 */
@Controller
public class IndexController {
  @Autowired
  private ProjectService projectService;

  @RequestMapping("/")
  public String indexFunction(Model model){
    List<Project> projects = projectService.findAll();
    model.addAttribute("projects", projects);
    return "index";
  }
  @RequestMapping("/collaborators")
  public String projects(Model model){
    return "collaborators";
  }
}
