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
        return "roles";
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

    @RequestMapping(value = "/roles/{roleId}/delete", method = RequestMethod.POST)
    public String deleteRole(@PathVariable Long roleId, RedirectAttributes redirectAttributes) {
        //TODO:mbj check for any associations to role before delete. return error message that role isn't empty.
//        if(cat.getGifs().size() > 0){
//            redirectAtributes.addFlashAttribute("flash", new FlashMessage("Only empty categories may be deleted", FlashMessage.Status.FAILURE));
//            return String.format("redirect:/categories/%s/edit", categoryId);
//        }
//
        roleService.delete(roleService.findById(roleId));
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category deleted!", FlashMessage.Status.SUCCESS));
        return "redirect:/roles";
    }
}
