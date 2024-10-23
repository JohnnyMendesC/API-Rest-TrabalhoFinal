package br.com.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.dto.ClienteRequestDTO;
import br.com.serratec.dto.ClienteResponseDTO;
import br.com.serratec.entity.Cliente;
import br.com.serratec.entity.Endereco;
import br.com.serratec.exception.EmailException;
import br.com.serratec.repository.ClienteRepository;
import br.com.serratec.repository.EnderecoRepository;

@Service
public class ClienteService {
//	REPOSITÓRIOS
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

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
		Optional<Cliente>u = repository.findByEmail(requisicaoDTO.getEmail());
		if(u.isPresent()) {//se ja existe o email então tem que throw exception
			throw new EmailException("Email indisponível!");	
		}
		// 2ºCRIA O ATRIBUTO QUE RECEBERÁ OS DADOS
		Cliente cliente = new Cliente();
		
		// 3ºINSTANCIA O ATRIBUTO COM OS DADOS DA REQUISIÇÃO
		//dados do cliente
		cliente.setNome(requisicaoDTO.getNome());
		cliente.setTelefone(requisicaoDTO.getTelefone());
		cliente.setEmail(requisicaoDTO.getEmail());
		cliente.setCpf(requisicaoDTO.getCpf());
		cliente.setNumeroResidencia(requisicaoDTO.getNumeroResidencia());
		cliente.setComplemento(requisicaoDTO.getComplemento());
		
		//dados do endereço do cliente
		Endereco endereco = enderecoRepository.findByCep(requisicaoDTO.getCep());
        if (endereco != null) {
            cliente.setEndereco(endereco);
        } else {
            RestTemplate rs = new RestTemplate();
            String uri = "https://viacep.com.br/ws/" + requisicaoDTO.getCep() + "/json/";
            Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
            if (enderecoViaCep.get().getCep() != null) {
                String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
                enderecoViaCep.get().setCep(cepSemTraco);

                endereco = new Endereco();
                endereco.setCep(enderecoViaCep.get().getCep());
                endereco.setBairro(enderecoViaCep.get().getBairro());
                endereco.setLocalidade(enderecoViaCep.get().getLocalidade());
                endereco.setLogradouro(enderecoViaCep.get().getLogradouro());
                endereco.setUf(enderecoViaCep.get().getUf());
                enderecoRepository.save(endereco);
                cliente.setEndereco(endereco);
            } else {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }

        }

        // 4ºSALVA NO BANCO COM TODOS OS DADOS INSERIDOS
        cliente = repository.save(cliente);
	
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
	 //encontrar
	 //valida se existe, puxa os dados para receber no optional
	 //lembrar do set id
	 

// DELETE | DELETAR
	/*
	 * @DeleteMapping("{id}") public ResponseEntity<Void> apagarItem(@PathVariable
	 * Long id)
	 */
}
