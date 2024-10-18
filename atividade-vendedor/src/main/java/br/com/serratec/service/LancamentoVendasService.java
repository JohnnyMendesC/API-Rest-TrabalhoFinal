package br.com.serratec.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.LancamentoVendasRequestDTO;
import br.com.serratec.dto.LancamentoVendasResponseDTO;
import br.com.serratec.entity.LancamentoVendas;
import br.com.serratec.entity.VendedorAutonomo;
import br.com.serratec.repository.LancamentoVendasRepository;
import jakarta.transaction.Transactional;

//service vai fazer o papel que a controller vinha fazendo
//conversando com o repositorio
@Service
public class LancamentoVendasService {
	
	@Autowired
	private LancamentoVendasRepository repository;
	//listar por id
	public List<LancamentoVendasResponseDTO> listar() {
        List<LancamentoVendas> LancamentoVendas = repository.findAll();
        List<LancamentoVendasResponseDTO> dtos = new ArrayList<>();
        for (LancamentoVendas LancamentoVenda : LancamentoVendas) {
            dtos.add(new LancamentoVendasResponseDTO(LancamentoVenda));
        }
        return dtos;
    }
	
	//inserir lançamento
	public LancamentoVendasResponseDTO inserir2(LancamentoVendas lancamentoVendas, VendedorAutonomo vendedorAutonomo) {
		VendedorAutonomo vendedor = vendedorAutonomo;
		lancamentoVendas = new LancamentoVendas();
		lancamentoVendas.setVendedorAutonomo(vendedor);
		return new LancamentoVendasResponseDTO(repository.save(lancamentoVendas));
    }
	
	@Transactional //se der certo ele da ok, se der erro ele bloqueia igual transferencia bancaria
	public LancamentoVendasResponseDTO inserir(LancamentoVendasRequestDTO dto) {
		//aqui o dto filtra, o usuario todo é salvo, e o dto
		LancamentoVendas lancamentoVendas = new LancamentoVendas();
		lancamentoVendas.setData(LocalDate.now());
		lancamentoVendas.setValor(dto.getValor());
		lancamentoVendas.setVendedorAutonomo(dto.getVendedorAutonomo());
		//vai como resposta com as infos sensiveis tratadas;
		lancamentoVendas = repository.save(lancamentoVendas);

		return new LancamentoVendasResponseDTO(lancamentoVendas);
	}
	
	//listar por pagina Page<lançamento>
    public Page<LancamentoVendas> findByVendedorAutonomo(@PageableDefault(page = 0, size = 10, 
    sort = "nome", direction = Direction.ASC) Pageable pageable) {
        return repository.findAll(pageable);
    }
}