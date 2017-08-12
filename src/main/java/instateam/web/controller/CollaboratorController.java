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


@Controller
public class CollaboratorController {
    @Autowired
    private CollaboratorService collaboratorService;
    @Autowired
    private RoleService roleService;

    // LIST ALL COLLABORATORS
    @RequestMapping("/collaborators")
    public String projects(Model model){
        if(!model.containsAttribute("collaborator")){
            model.addAttribute("collaborator", new Collaborator());
        }
        model.addAttribute("collaborators", collaboratorService.findAll());
        model.addAttribute("roles", roleService.findAll());
        return "/collaborator/collaborators";
    }
    // ADD NEW COLLABORATOR
    @RequestMapping(value = "/collaborators", method = RequestMethod.POST)
    public String addCollaborator(@Valid Collaborator collaborator, BindingResult bindingResult, RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.collaborator", bindingResult);
            attributes.addFlashAttribute("collaborator", collaborator);
            return "redirect:/collaborators";
        }
        collaboratorService.save(collaborator);
        return "redirect:/collaborators";
    }

    // GET DETAILS/EDIT COLLABORATOR
    @RequestMapping("/collaborators/{collaboratorId}/edit")
    public String editCollaborator(@PathVariable Long collaboratorId, Model model){
        if(!model.containsAttribute("collaborator")){
            model.addAttribute("collaborator", collaboratorService.findById(collaboratorId));
        }
        model.addAttribute("roles", roleService.findAll());
        return "/collaborator/edit_collaborator";
    }

    // EDIT/SAVE CHANGES TO COLLABORATOR
    @RequestMapping(value = "/collaborators/{collaboratorId}/edit", method = RequestMethod.POST)
    public String updateCollaborator(@Valid Collaborator collaborator, BindingResult bindingResult, RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.collaborator", bindingResult);
            attributes.addFlashAttribute("collaborator", collaborator);
            return String.format("redirect:/collaborators/%s/edit", collaborator.getId());
        }
        Collaborator existingCollaborator = collaboratorService.findById(collaborator.getId());
        existingCollaborator.setName(collaborator.getName());
        existingCollaborator.setRole(collaborator.getRole());
        collaboratorService.save(existingCollaborator);
        return "redirect:/collaborators";
    }
}
