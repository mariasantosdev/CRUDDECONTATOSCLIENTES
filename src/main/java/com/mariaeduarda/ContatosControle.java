package com.mariaeduarda;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContatosControle {

	private static final ArrayList<Contato> LISTA_CONTATOS = new ArrayList<Contato>();
	
	static {
		LISTA_CONTATOS.add(new Contato("1", "Maria", "119657886890"));
		LISTA_CONTATOS.add(new Contato("2", "Fernando", "119037893"));
		LISTA_CONTATOS.add(new Contato("3", "Gabriela", "26948379293"));
		LISTA_CONTATOS.add(new Contato("4", "Leticia", "1196738936"));
		LISTA_CONTATOS.add(new Contato("5", "Alex", "51968396280"));
		
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/contatos")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("listar");
		
		modelAndView.addObject("contatos", LISTA_CONTATOS);
		
		return modelAndView;
	}
}
