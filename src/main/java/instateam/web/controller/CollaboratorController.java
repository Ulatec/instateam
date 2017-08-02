package instateam.web.controller;

import instateam.model.Collaborator;
import instateam.service.CollaboratorService;
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

/**
 * Created by mark on 7/18/2017.
 */
@Controller
public class CollaboratorController {
    @Autowired
    private CollaboratorService collaboratorService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/collaborators")
    public String projects(Model model){
        if(!model.containsAttribute("newCollaborator")){
            model.addAttribute("newCollaborator", new Collaborator());
        }
        model.addAttribute("collaborators", collaboratorService.findAll());
        model.addAttribute("roles", roleService.findAll());
        return "/collaborator/collaborators";
    }

    @RequestMapping(value = "/collaborators", method = RequestMethod.POST)
    public String addCollaborator(@Valid Collaborator collaborator, BindingResult bindingResult, RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            attributes.addFlashAttribute("org.springframework.", bindingResult);
            attributes.addFlashAttribute("collaborator", collaborator);
        }
        collaboratorService.save(collaborator);
        return "redirect:/";
    }
    @RequestMapping("/collaborators/{collaboratorId}/edit")
    public String editCollaborator(@PathVariable Long collaboratorId, Model model){
        if(!model.containsAttribute("collaborator")){
            model.addAttribute("collaborator", collaboratorService.findById(collaboratorId));
        }
        model.addAttribute("roles", roleService.findAll());
        return "/collaborator/edit_collaborator";
    }
    @RequestMapping(value = "/collaborators/{collaboratorId}/edit", method = RequestMethod.POST)
    public String updateCollaborator(@Valid Collaborator collaborator, BindingResult bindingResult, RedirectAttributes attributes){
        Collaborator existingCollaborator = collaboratorService.findById(collaborator.getId());
        existingCollaborator.setName(collaborator.getName());
        existingCollaborator.setRole(collaborator.getRole());
        collaboratorService.save(existingCollaborator);
        return "redirect:/collaborators";
    }
}
