package instateam.controller;

import instateam.model.Role;
import instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String newRoles(@Valid Role role, BindingResult bindingResult, RedirectAttributes attributes){
        roleService.save(role);
        return "redirect:/roles";
    }
}
