package com.AppHotel.AppHotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.AppHotel.AppHotel.models.Funcionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Long>{
	
	Funcionario findById(long id);
	Funcionario findByNome(String nome);
	
	// Query para a busca
	@Query(value = "select u from Funcionario u where u.nome like %?1%")
	List<Funcionario>findByNomes(String nome);

}
