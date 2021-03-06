package instateam.web.controller;

import instateam.model.Collaborator;
import instateam.model.Role;
import instateam.service.RoleService;
import instateam.web.Status;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ProjectController {


  @Autowired
  private ProjectService projectService;
  @Autowired
  private CollaboratorService collaboratorService;
  @Autowired
  private RoleService roleService;

  // LIST ALL PROJECTS / INDEX PAGE
  @RequestMapping("/")
  public String indexFunction(Model model){
    model.addAttribute("projects", projectService.findAll());
    return "index";
  }

  // ADD NEW PROJECT FORM
  @RequestMapping("/projects/add")
  public String formNewProject(Model model){
    if(!model.containsAttribute("project")){
      model.addAttribute("project", new Project());
    }
    model.addAttribute("submit", "Add");
    model.addAttribute("roles", roleService.findAll());
    return "project/edit_project";
  }

  // ADD NEW PROJECT
  @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
  public String addProject(@Valid Project project, BindingResult bindingResult, RedirectAttributes redirectAttributes){

    if(bindingResult.hasErrors()){
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.project", bindingResult);
      redirectAttributes.addFlashAttribute("project", project);
      return "redirect:/projects/add";
    }
    projectService.save(project);


    return "redirect:/";
  }

  // EDIT PROJECT DETAILS
  @RequestMapping("/project/{projectId}/edit")
  public String editProject(@PathVariable Long projectId, Model model){
    if(!model.containsAttribute("project")){
      model.addAttribute("project", projectService.findById(projectId));
    }
    model.addAttribute("collaborators", collaboratorService.findAll());
    model.addAttribute("submit", "Update");
    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("statuses", Status.values());
    return "project/edit_project";
  }

  // EDIT PROJECT COLLABORATORS
  @RequestMapping("/project/{projectId}/editCollaborators")
  public String editProjectCollaborators(@PathVariable Long projectId, Model model){
    if(!model.containsAttribute("project")){
      model.addAttribute("project", projectService.findById(projectId));
    }
    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("collaborators", collaboratorService.findAll());
    return "project/project_collaborators";
  }

  // SAVE CHANGES TO PROJECT
  @RequestMapping(value = "/project/{projectId}/edit", method = RequestMethod.POST)
  public String updateProject(@Valid Project project, BindingResult bindingResult, RedirectAttributes redirectAttributes){
    if(bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.project", bindingResult);
      redirectAttributes.addFlashAttribute("project", project);
      return String.format("redirect:/project/%s/edit", project.getId());
    }
      Project existingProject = projectService.findById(project.getId());
      existingProject.setName(project.getName());
      existingProject.setDescription(project.getDescription());
      existingProject.setRolesNeeded(project.getRolesNeeded());
      existingProject.setCollaborators(project.getCollaborators());
      existingProject.setStatus(project.getStatus());
      projectService.save(existingProject);
      return String.format("redirect:/project/%s/details", project.getId());
  }

  // SAVE CHANGES TO PROJECT COLLABORATORS
  @RequestMapping(value = "/project/{projectId}/editCollaborators", method = RequestMethod.POST)
  public String updateCollaborators(@Valid Project project, BindingResult bindingResult){
    if(bindingResult.hasErrors()) {
    }else{
      List<Role> roles = project.getRolesNeeded();
      List<Role> newRoles = new ArrayList<>();
      for (Role role : roles) {
        newRoles.add(roleService.findById(role.getId()));
      }
      project.setRolesNeeded(newRoles);
      Project existingProject =  projectService.findById(project.getId());
      existingProject.setRolesNeeded(project.getRolesNeeded());
      existingProject.setCollaborators(project.getCollaborators());
      projectService.save(project);
    }
    return String.format("redirect:/project/%s/detail", project.getId());
}
  // GET PROJECT DETAILS
  @RequestMapping("/project/{projectId}/detail")
  public String projectDetails(@PathVariable Long projectId, Model model){
    Project project = projectService.findById(projectId);

    Map<Role, Collaborator> rolesAssignment = getRoleCollaboratorMap(project);

    model.addAttribute("project", project);
    model.addAttribute("rolesAssignment", rolesAssignment);
    return "project/project_detail";
  }

  // BUILD MAP OF ROLES WITH CORRESPONDING COLLABORATORS
  private Map<Role, Collaborator> getRoleCollaboratorMap(Project project) {
    List<Role> rolesNeeded = project.getRolesNeeded();
    List<Collaborator> collaborators = project.getCollaborators();
    Map<Role, Collaborator> roleCollaborator = new LinkedHashMap<>();
    for (Role role : rolesNeeded) {
      roleCollaborator.put(role,
              collaborators.stream()
                      .filter((col) -> col.getRole().getId().equals(role.getId()))
                      .findFirst()
                      .orElseGet(() -> {
                        Collaborator unassigned = new Collaborator();
                        unassigned.setName("[Unassigned]");
                        return unassigned;
                      }));
    }
    return roleCollaborator;
  }
}
