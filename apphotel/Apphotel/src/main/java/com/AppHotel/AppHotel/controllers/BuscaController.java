package com.AppHotel.AppHotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AppHotel.AppHotel.models.Candidato;
import com.AppHotel.AppHotel.models.Dependente;
import com.AppHotel.AppHotel.models.Hospede;
import com.AppHotel.AppHotel.models.Funcionario;
import com.AppHotel.AppHotel.models.Vaga;
import com.AppHotel.AppHotel.repository.CandidatoRepository;
import com.AppHotel.AppHotel.repository.DependenteRepository;
import com.AppHotel.AppHotel.repository.HospedeRepository;
import com.AppHotel.AppHotel.repository.FuncionarioRepository;
import com.AppHotel.AppHotel.repository.VagaRepository;


@Controller
public class BuscaController {
	
	@Autowired
	private FuncionarioRepository fr;
	
	@Autowired
	private VagaRepository vr;
	
	@Autowired
	private DependenteRepository dr;
	
	@Autowired
	private CandidatoRepository cr;
	
	@Autowired
	private HospedeRepository hr;
	
	//GET
	@RequestMapping("/")
	public ModelAndView abrirIndex() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	//POST
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView buscarIndex(@RequestParam("buscar") String buscar, @RequestParam("nome") String nome) {
		
		ModelAndView mv = new ModelAndView("index");
		String mensagem = "Resultados da busca por " + buscar;
		
		if(nome.equals("nomefuncionario")) {
			mv.addObject("funcionarios", fr.findByNomes(buscar));
			
		}else if(nome.equals("nomedependente")) {
			mv.addObject("dependentes", dr.findByNomesDependentes(buscar));
			
		}else if(nome.equals("nomecandidato")) {
			mv.addObject("candidatos", cr.findByNomesCandidatos(buscar));
			
		}else if(nome.equals("titulovaga")) {
			mv.addObject("vagas", vr.findByNomesVaga(buscar));
			
		}else if(nome.equals("nomehospede")) {
			mv.addObject("hospedes", hr.findByNomesHospedes(buscar));
			
		}else {
			mv.addObject("funcionarios", fr.findByNomes(buscar));
			mv.addObject("dependentes", dr.findByNomesDependentes(buscar));
			mv.addObject("candidatos", cr.findByNomesCandidatos(buscar));
			mv.addObject("vagas", vr.findByNomesVaga(buscar));
			mv.addObject("hospedes", hr.findByNomesHospedes(buscar));
		}
		
		mv.addObject("mensagem", mensagem);
		
		return mv;
	}

}
