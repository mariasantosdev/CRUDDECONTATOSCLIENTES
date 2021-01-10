package com.mariaeduarda;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/contatos/novo")
	public ModelAndView novo() {
		
		ModelAndView modelAndView = new ModelAndView("Formulario");
		
		modelAndView.addObject("contato", new Contato());
		
		return modelAndView;
	}	
	
	@PostMapping("/contatos")
	public String cadastrar(Contato contato) {
	
		String id = UUID.randomUUID().toString();

		contato.setId(id);

		LISTA_CONTATOS.add(contato);
		
		return "redirect:/contatos";
	}
	
	
	@GetMapping("/contatos/{id}/editar")
	public ModelAndView editar(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView("Formulario");
		
	
		Contato contato = procurarContato(id);
		
		modelAndView.addObject("contato", contato);
		
		return modelAndView;
	}
	
	@PostMapping("/contatos/{id}")
	public String atualizar(Contato contato) {
		Integer indice = procurarIndiceContato(contato.getId());
		
		Contato contatoVelho = LISTA_CONTATOS.get(indice);
		
		
		LISTA_CONTATOS.remove(contatoVelho);
		
		LISTA_CONTATOS.add(indice, contato);
		
		return "redirect:/contatos";
	}
	
	@DeleteMapping("/contatos/{id}")
	public String remover(@PathVariable String id) {
		Contato contato = procurarContato(id);
		
		LISTA_CONTATOS.remove(contato);
		
		return "redirect:/contatos";
	}
	
	
	private Contato procurarContato(String id) {
		Integer indice = procurarIndiceContato(id);
		
		if (indice != null) {
			Contato contato = LISTA_CONTATOS.get(indice);
			return contato;
		}
		
		return null;
	}
	
	private Integer procurarIndiceContato(String id) {
		for (int i = 0; i < LISTA_CONTATOS.size(); i++) {
			Contato contato = LISTA_CONTATOS.get(i);
			
			if (contato.getId().equals(id)) {
				return i;
			}
		}
		
		return null;
	}

}
