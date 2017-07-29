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

import javax.validation.Valid;
import instateam.model.Project;
import instateam.service.CollaboratorService;
import instateam.service.ProjectService;

import java.util.List;


@Controller
public class ProjectController {


  @Autowired
  private ProjectService projectService;
  @Autowired
  private CollaboratorService collaboratorService;
  @Autowired
  private RoleService roleService;


  @RequestMapping("/")
  public String indexFunction(Model model){
    model.addAttribute("projects", projectService.findAll());
    return "index";
  }
  @RequestMapping("/projects/add")
  public String formNewProject(Model model){
    if(!model.containsAttribute("project")){
      model.addAttribute("project", new Project());
    }
    model.addAttribute("submit", "Add");
    model.addAttribute("roles", roleService.findAll());
    return "project/edit_project";
  }
  @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
  public String addProject(@Valid Project project, BindingResult bindingResult, RedirectAttributes attributes){
    System.out.println("save");
    System.out.println(project.getRolesNeeded());
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
    model.addAttribute("roles", roleService.findAll());
    return "project/edit_project";
  }
  @RequestMapping("/project/{projectId}/editCollaborators")
  public String editProjectCollaborators(@PathVariable Long projectId, Model model){
//    if(!model.containsAttribute("project")){
//      model.addAttribute("project", projectService.findById(projectId));
//    }
//    model.addAttribute("collaborators", collaboratorService.findAll());
//    model.addAttribute("submit", "Update");
//    model.addAttribute("roles", roleService.findAll());
    return "project/project_collaborators";
  }

  @RequestMapping(value = "/project/{projectId}/edit", method = RequestMethod.POST)
  public String updateProject(@Valid Project project){
    Project existingProject = projectService.findById(project.getId());
    existingProject.setName(project.getName());
    existingProject.setDescription(project.getDescription());
    existingProject.setRolesNeeded(project.getRolesNeeded());
    existingProject.setCollaborators(project.getCollaborators());
    existingProject.setStatus(project.getStatus());
    projectService.save(existingProject);
    return "redirect:/";
  }

  @RequestMapping("/project/{projectId}/detail")
  public String projectDetails(@PathVariable Long projectId, Model model){
    model.addAttribute("project", projectService.findById(projectId));
    return "project/project_detail";
  }

}
