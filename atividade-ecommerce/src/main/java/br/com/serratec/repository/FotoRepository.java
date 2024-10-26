package br.com.serratec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.serratec.entity.Foto;
import br.com.serratec.entity.Funcionario;


public interface FotoRepository extends JpaRepository<Foto, Long>{
	public Optional<Foto> findByFuncionario(Funcionario funcionario);
	
	@Query(nativeQuery = true, value = "select * from foto where foto.id_funcionario=:idFuncionario and foto.id=:idFoto")
	public Foto buscarFotoFuncionario(Long idFuncionario, Long idFoto);
}