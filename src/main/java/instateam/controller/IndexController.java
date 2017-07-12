package instateam.controller;

import instateam.model.Role;
import instateam.service.RoleService;
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
import instateam.model.Collaborator;
import instateam.model.Project;
import instateam.service.CollaboratorService;
import instateam.service.ProjectService;


@Controller
public class IndexController {
  @Autowired
  private ProjectService projectService;
  @Autowired
  private CollaboratorService collaboratorService;
  @Autowired
  private RoleService roleService;
  @RequestMapping("/")
  public String indexFunction(Model model){
    List<Project> projects = projectService.findAll();
    model.addAttribute("projects", projects);
    return "index";
  }
  @RequestMapping("/collaborators")
  public String projects(Model model){
    if(!model.containsAttribute("collaborator")){
      model.addAttribute("collaborator", new Collaborator());
    }
    model.addAttribute("collaborators", collaboratorService.findAll());
    return "collaborators";
  }

  @RequestMapping(value = "/collaborators", method = RequestMethod.POST)
  public String addCollaborator(@Valid Collaborator collaborator, BindingResult bindingResult, RedirectAttributes attributes){
    collaboratorService.save(collaborator);
    return "redirect:/";
  }
  @RequestMapping("/projects/add")
  public String formNewProject(Model model){
    if(!model.containsAttribute("project")){
      model.addAttribute("project", new Project());
    }
    model.addAttribute("submit", "Add");
    return "edit_project";
  }
  @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
  public String addProject(@Valid Project project, BindingResult bindingResult, RedirectAttributes attributes){
    System.out.println("save");
    projectService.save(project);
    return "redirect:/";
  }
  @RequestMapping("/project/{projectId}/edit")
  public String editProject(@PathVariable Long projectId, Model model){
    if(!model.containsAttribute("project")){
      model.addAttribute("project", projectService.findById(projectId));
    }
    model.addAttribute("collaborators", collaboratorService.findAll());
    model.addAttribute("submit", "Update");
    return "edit_project";
  }
  @RequestMapping("/project/{projectId}/detail")
  public String projectDetails(@PathVariable Long projectId, Model model){
    model.addAttribute("project", projectService.findById(projectId));
    return "project_detail";
  }
  @RequestMapping("/roles")
  public String roles(Model model){
    if(!model.containsAttribute("role")){
      model.addAttribute("role", new Role());
    }
    model.addAttribute("roles", roleService.findAll());
    return "roles";
  }
  @RequestMapping(value = "/roles", method=RequestMethod.POST)
  public String newRoles(@Valid Role role, BindingResult bindingResult, RedirectAttributes attributes){
    roleService.save(role);
    return "redirect:/roles";
  }
}
