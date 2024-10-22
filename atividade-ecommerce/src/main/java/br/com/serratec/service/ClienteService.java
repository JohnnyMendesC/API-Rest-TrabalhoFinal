package br.com.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.serratec.dto.ClienteRequestDTO;
import br.com.serratec.dto.ClienteResponseDTO;
import br.com.serratec.entity.Cliente;
import br.com.serratec.repository.ClienteRepository;

@Service
public class ClienteService {
//	REPOSITÓRIOS
	@Autowired
	private ClienteRepository repository;

//	REGRAS DE NEGÓCIO
	// GET ALL | LISTAR TODOS
	public List<ClienteResponseDTO> listarTodosClientes() {

		List<Cliente> clientes = repository.findAll();
		List<ClienteResponseDTO> respostasDTO = new ArrayList<ClienteResponseDTO>();

		for (Cliente resposta : clientes) {
			respostasDTO.add(new ClienteResponseDTO(resposta));
		}
		return respostasDTO;
	}
	
	// GET ID | LISTAR POR ID	
	@GetMapping("{id}")
	public ClienteResponseDTO exibirCliente(@PathVariable Long	id){
		Optional<Cliente> cliente = repository.findById(id);
		ClienteResponseDTO dto = new ClienteResponseDTO();
		dto.setNome(cliente.get().getNome());
		dto.setEmail(cliente.get().getEmail());
		dto.setTelefone(cliente.get().getTelefone());
		return dto;
	}
	 

	// POST | INSERIR
	public ClienteResponseDTO inserirCliente(ClienteRequestDTO requisicaoDTO) {
		// 1ºBUSCA EXCEPTIONS
		// if (!user.getSenha().equalsIgnoreCase(user.getConfirmaSenha())) {throw new
		// SenhaException("Senha e Confirma Senha não são iguais");}
		// if (usuarioRepository.findByEmail(user.getEmail())!=null) {throw new
		// EmailException("Email já existente");}
		// 2ºCRIA O ATRIBUTO QUE RECEBERÁ OS DADOS
		Cliente cliente = new Cliente();
		// 3ºINSTANCIA O ATRIBUTO COM OS DADOS DA REQUISIÇÃO
//		cliente.setX(requisicaoDTO.getX());
//		cliente.setY(requisicaoDTO.getY());
//		cliente.setZ(requisicaoDTO.getZ());
		// 4ºSALVA NO BANCO COM TODOS OS DADOS INSERIDOS
		repository.save(cliente);
		// 5ºRETORNA NO FRONT OS DADOS FILTRADOS COM O CONSTRUTOR DE responseDTO
		return new ClienteResponseDTO(cliente);
	}

// POST GROUP | POSTAR VÁRIOS
	/*
	 * @PostMapping("/varios") criarVariosItens(@RequestBody List<Item> itens) {
	 * return itemRepository.saveAll(itens);
	 */

// PUT | ALTERAR	
	 @PutMapping("{id}") public ResponseEntity<Cliente> alterar
	 (@PathVariable Long id, @RequestBody Cliente clienteModificado) {
		 return null;
	 }

// DELETE | DELETAR
	/*
	 * @DeleteMapping("{id}") public ResponseEntity<Void> apagarItem(@PathVariable
	 * Long id)
	 */
}
