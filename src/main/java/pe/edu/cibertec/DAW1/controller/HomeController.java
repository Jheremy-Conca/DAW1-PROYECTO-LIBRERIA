package pe.edu.cibertec.DAW1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class HomeController {

	@GetMapping("/home")
	public String goHome(Model model) {
		return "home";
	}

}
