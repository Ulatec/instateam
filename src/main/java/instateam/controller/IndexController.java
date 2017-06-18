package instateam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import javax.validation.Valid;
import instateam.model.Project;
import instateam.service.ProjectService;

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
  @RequestMapping("/projects/add")
  public String formNewProject(Model model){
    if(!model.containsAttribute("project")){
      model.addAttribute("project", new Project());
    }
    return "edit_project";
  }
  @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
  public String addProject(@Valid Project project, BindingResult bindingResult, RedirectAttributes attributes){
    projectService.save(project);
    return "redirect:/";
  }
  @RequestMapping("/project/{projectId}/edit")
  public String editProject(@PathVariable Long projectId){


    return "redirect:/";
  }

}
