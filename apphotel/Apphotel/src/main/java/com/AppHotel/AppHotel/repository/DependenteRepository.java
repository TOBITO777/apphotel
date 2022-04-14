package com.AppHotel.AppHotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.AppHotel.AppHotel.models.Dependente;
import com.AppHotel.AppHotel.models.Funcionario;

public interface DependenteRepository extends CrudRepository<Dependente, Long> {

	Iterable<Dependente> findByFuncionario(Funcionario funcionario);

	// para o método delete por CPF
	Dependente findByCpf(String cpf);
	
	Dependente findById(long id);
	List<Dependente> findByNome(String nome);

	// Query para a busca
	@Query(value = "select u from Dependente u where u.nome like %?1%")
	List<Dependente> findByNomesDependentes(String nome);

}
