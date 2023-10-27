package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/form")
	public String form() {
		return "ComplainForm";
	}
	
	@RequestMapping("/thankyou")
	public String thankyou() {
		return "FormSubmit";
	}
	
}
