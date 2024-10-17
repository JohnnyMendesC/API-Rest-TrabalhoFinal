package br.com.serratec.service;

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
import br.com.serratec.repository.LancamentoVendasRepository;

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
	public LancamentoVendasRequestDTO inserir(LancamentoVendas lancamentoVendas) {
        return new LancamentoVendasRequestDTO(repository.save(lancamentoVendas));
    }
	
	//listar por pagina Page<lançamento>
    public Page<LancamentoVendas> findByVendedor(@PageableDefault(page = 0, size = 10, 
    sort = "nome", direction = Direction.ASC) Pageable pageable) {
        return repository.findAll(pageable);
    }
}
