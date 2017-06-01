package instateam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mark on 5/31/2017.
 */
@Controller
public class IndexController {
  @RequestMapping("/")
  public String indexFunction(Model model){
    return "index";
  }
}
