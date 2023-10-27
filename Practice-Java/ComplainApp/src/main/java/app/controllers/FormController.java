package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

	@PostMapping("/submit")
	public String submit() {
		return "redirect:/thanks";
	}
	
}
