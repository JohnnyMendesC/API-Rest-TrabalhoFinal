package br.com.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.serratec.dto.PedidoRequestDTO;
import br.com.serratec.dto.PedidoResponseDTO;
import br.com.serratec.entity.Pedido;
import br.com.serratec.enums.StatusPedido;
import br.com.serratec.repository.ClienteRepository;
import br.com.serratec.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

       

	public List<PedidoResponseDTO> listarTodosPedido() {

		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoResponseDTO> respostasDTO = new ArrayList<PedidoResponseDTO>();

		for (Pedido resposta : pedidos) {
			respostasDTO.add(new PedidoResponseDTO(resposta));
		}
		return respostasDTO;
	}
	
	// GET ID | LISTAR POR ID
	@GetMapping("{id}")
	public PedidoResponseDTO exibirPedido(@PathVariable Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		PedidoResponseDTO dto = new PedidoResponseDTO();
		dto.setNome(pedido.get().getCliente().getNome());
		dto.setEmail(pedido.get().getCliente().getEmail());
		dto.setTelefone(pedido.get().getCliente().getTelefone());
		dto.setStatus(pedido.get().getStatus());
		return dto;
	}
    
    // Inserir um novo pedido (POST)
    public PedidoResponseDTO inserirPedido(PedidoRequestDTO requisicaoDTO) {
        var pedido = new Pedido();
        // Definir o cliente do pedido (você pode ajustar conforme sua lógica de cliente)
        pedido.setCliente(clienteRepository.findById(requisicaoDTO.getClienteId()).orElseThrow());

       // pedido.setData(requisicaoDTO.getData());
       // pedido.setHora(requisicaoDTO.getHora());

        if (requisicaoDTO.getStatus() != null) {
            pedido.setStatus(requisicaoDTO.getStatus());
        } else {
            pedido.setStatus(StatusPedido.REALIZADO); // Status padrão
        }
        pedido = pedidoRepository.save(pedido);
       
        return new PedidoResponseDTO(pedido);
    }

    // Atualizar um pedido existente (PUT)
    public PedidoResponseDTO atualizarPedido(Long id, PedidoRequestDTO pedidoModificado) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isEmpty()) {
            throw new RuntimeException("Pedido não encontrado.");
        }

        Pedido pedido = pedidoOptional.get();
        // Atualizar data e hora
        //pedido.setData(pedidoModificado.getData());
        //pedido.setHora(pedidoModificado.getHora());

       
        if (pedidoModificado.getStatus() != null) {
            pedido.setStatus(pedidoModificado.getStatus());
        }

        
        pedido = pedidoRepository.save(pedido);

        
        return new PedidoResponseDTO(pedido);
    }
}