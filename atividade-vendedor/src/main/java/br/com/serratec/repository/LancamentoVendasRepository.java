package br.com.serratec.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.LancamentoVendas;

public interface LancamentoVendasRepository extends JpaRepository<LancamentoVendas, Long>{	
	Page<LancamentoVendas> findAll(Pageable pageable);
	List<LancamentoVendas> findByVendedorAutonomoId(Long vendedorId);
}
