package com.AppHotel.AppHotel.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppHotel.AppHotel.models.Hospede;
import com.AppHotel.AppHotel.repository.HospedeRepository;


@Controller
public class HospedeController {
	
	@Autowired
	private HospedeRepository hr;
	
	// GET que chama o form para cadastrar hospede
	@RequestMapping("/cadastrarHospede")
	public String form() {
		return "hospede/form-hospede";
	}

	// POST que cadastra hospede
	@RequestMapping(value = "/cadastrarHospede", method = RequestMethod.POST)
	public String form(@Valid Hospede hospede, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarHospede";
		}

		hr.save(hospede);
		attributes.addFlashAttribute("mensagem", "Hospede cadastrado com sucesso!");
		return "redirect:/cadastrarHospede";
	}
	// GET que lista hospedes
	@RequestMapping("/hospedes")
	public ModelAndView listaHospedes() {
		ModelAndView mv = new ModelAndView("hospede/lista-hospede");
		Iterable<Hospede> hospedes = hr.findAll();
		mv.addObject("hospedes", hospedes);
		return mv;
	}
	
	// GET que lista os hospedes
		@RequestMapping("/detalhes-hospede/{id}")
		public ModelAndView detalhesHospede(@PathVariable("id") long id) {
			Hospede hospede = hr.findById(id);
			ModelAndView mv = new ModelAndView("hospede/detalhes-hospede");
			mv.addObject("hospedes", hospede);

			return mv;

		}
		
	//GET que deleta hospede
	@RequestMapping("/deletarHospede")
	public String deletarHospede(long id) {
		Hospede hospede = hr.findById(id);
		hr.delete(hospede);
		return "redirect:/hospedes";
		
	}
	
	// Métodos que atualizam hospede
	// GET que chama o FORM de edição do hospede
	@RequestMapping("/editar-hospede")
	public ModelAndView editarHospede(long id) {
		Hospede hospede = hr.findById(id);
		ModelAndView mv = new ModelAndView("hospede/update-hospede");
		mv.addObject("hospede", hospede);
		return mv;
	}
	// POST que atualiza a hospede
	@RequestMapping(value = "/editar-hospede", method = RequestMethod.POST)
	public String updateHospede(@Valid Hospede hospede,  BindingResult result, RedirectAttributes attributes){
		
		hr.save(hospede);
		attributes.addFlashAttribute("success", "Hospede alterado com sucesso!");
		
		long idLong = hospede.getId();
		String id = "" + idLong;
		return "redirect:/detalhes-hospede/" + id;
		
	}
	
}
