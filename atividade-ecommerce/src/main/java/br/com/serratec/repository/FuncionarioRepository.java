package br.com.serratec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	Optional<Funcionario> findByNome(String nome);

	Optional<Funcionario> findByCargo(String cargo);
}