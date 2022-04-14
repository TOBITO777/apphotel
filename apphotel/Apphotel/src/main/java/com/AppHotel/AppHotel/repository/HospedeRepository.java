package com.AppHotel.AppHotel.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.AppHotel.AppHotel.models.Hospede;


public interface HospedeRepository extends CrudRepository <Hospede, Long>{
	
	Hospede findById(long id);
	Hospede findByNome(String nome);
	
	// Query para a busca
	@Query(value = "select u from Hospede u where u.nome like %?1%")
	List<Hospede>findByNomesHospedes(String nome);
}
