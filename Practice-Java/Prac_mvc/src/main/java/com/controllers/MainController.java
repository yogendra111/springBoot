package com.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entities.Reservation;

@Controller
public class MainController {

	@ModelAttribute
	public void name(Model m) {
		m.addAttribute("name", "Yogendra");
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	@RequestMapping("/form")
	public String form(Model m) {
		m.addAttribute("reservation", new Reservation());
		return "form";
	}
	@RequestMapping("/submit")
	public String submit(@ModelAttribute("reservation") Reservation res) {

//        String name=req.getParameter("name");  
//        String pass=req.getParameter("pass");
//        System.out.println("name: " + name + "pass: " + pass);
//        m.addAttribute(name);
        
		return "thanks";
	}
	
	@RequestMapping("/thanks")
	public String thanks() {
		return "thanks";
	}
	
}
