package instateam.web.controller;

import instateam.model.Role;
import instateam.service.RoleService;
import instateam.web.FlashMessage;
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
public class RoleController {
    @Autowired
    private RoleService roleService;

    //ROLES
    @RequestMapping("/roles")
    public String roles(Model model){
        if(!model.containsAttribute("role")){
            model.addAttribute("role", new Role());
        }
        model.addAttribute("roles", roleService.findAll());
        return "/role/roles";
    }
    @RequestMapping(value = "/roles", method= RequestMethod.POST)
    public String newRoles(@Valid Role role, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.role", bindingResult);
            redirectAttributes.addFlashAttribute("role", role);
            return "redirect:/roles";
        }
        roleService.save(role);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role successfully added.", FlashMessage.Status.SUCCESS));
        return "redirect:/roles";
    }

    @RequestMapping(value = "/roles/{roleId}/edit", method = RequestMethod.POST)
    public String updateRole(@Valid Role role, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.role", bindingResult);
            redirectAttributes.addFlashAttribute("role", role);
            return String.format("redirect:/roles/%s/detail", role.getId());
        }
        Role existingRole = roleService.findById(role.getId());
        existingRole.setName(role.getName());
        roleService.save(existingRole);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role Updated!", FlashMessage.Status.SUCCESS));
        return "redirect:/roles";
    }
    @RequestMapping("/roles/{roleId}/detail")
    public String roleDetail(@PathVariable Long roleId, Model model){
        model.addAttribute("role", roleService.findById(roleId));
        model.addAttribute("title", "Edit " + roleService.findById(roleId).getName());
        return "/role/detail";
    }
}
