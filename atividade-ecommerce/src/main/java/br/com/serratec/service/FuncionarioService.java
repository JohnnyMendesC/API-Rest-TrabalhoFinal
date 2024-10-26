package br.com.serratec.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.dto.FuncionarioRequestDTO;
import br.com.serratec.dto.FuncionarioResponseDTO;
import br.com.serratec.entity.Endereco;
import br.com.serratec.entity.Funcionario;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.EnderecoRepository;
import br.com.serratec.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {
	//REPOSITORIOS SERVIÇOS
	@Autowired
	private FuncionarioRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private FotoService fotoService;


	//
	//	METODO PRO POST
	//
	@Transactional
	public FuncionarioResponseDTO inserir(FuncionarioRequestDTO dto, MultipartFile file) throws IOException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(dto.getNome());
		funcionario.setTelefone(dto.getTelefone());
		funcionario.setCargo(dto.getCargo());
		funcionario.setNumeroResidencia(dto.getNumeroResidencia());
		funcionario.setComplemento(dto.getComplemento());
		

		Endereco endereco = enderecoRepository.findByCep(dto.getCep());
		if (endereco != null) {
			funcionario.setEndereco(endereco);
		} else {
			RestTemplate rs = new RestTemplate();
			String uri = "https://viacep.com.br/ws/" + dto.getCep() + "/json/";
			Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				endereco = new Endereco();
				endereco.setCep(enderecoViaCep.get().getCep());
				endereco.setBairro(enderecoViaCep.get().getBairro());
				endereco.setLocalidade(enderecoViaCep.get().getLocalidade());
				endereco.setLogradouro(enderecoViaCep.get().getUf());
				enderecoRepository.save(endereco);
			} else {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}

		}
		funcionario = repository.save(funcionario);
		fotoService.inserir(funcionario, file);
		return adicionarUrlFoto(funcionario);
	}

	//
	//	METODO PRO PUT
	//
	@Transactional
	public FuncionarioResponseDTO atualizarFuncionario(Long id, FuncionarioRequestDTO dto, MultipartFile file) throws IOException {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(dto.getNome());
		funcionario.setTelefone(dto.getTelefone());
		funcionario.setCargo(dto.getCargo());
		funcionario.setNumeroResidencia(dto.getNumeroResidencia());
		funcionario.setComplemento(dto.getComplemento());
		

		Endereco endereco = enderecoRepository.findByCep(dto.getCep());
		if (endereco != null) {
			funcionario.setEndereco(endereco);
		} else {
			RestTemplate rs = new RestTemplate();
			String uri = "https://viacep.com.br/ws/" + dto.getCep() + "/json/";
			Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				endereco = new Endereco();
				endereco.setCep(enderecoViaCep.get().getCep());
				endereco.setBairro(enderecoViaCep.get().getBairro());
				endereco.setLocalidade(enderecoViaCep.get().getLocalidade());
				endereco.setLogradouro(enderecoViaCep.get().getUf());
				enderecoRepository.save(endereco);
			} else {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}

		}
		funcionario = repository.save(funcionario);
		fotoService.inserir(funcionario, file);
		return adicionarUrlFoto(funcionario);
	}
	
	
	//
	//	METODO PRO DELETE
	//
	@Transactional
	public ResponseEntity<FuncionarioResponseDTO> removerFuncionario(Long id, FuncionarioRequestDTO dto, MultipartFile file) throws IOException {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(null);
		funcionario.setTelefone(null);
		funcionario.setCargo(null);
		funcionario.setNumeroResidencia(null);
		funcionario.setComplemento(null);
		

		Endereco endereco = enderecoRepository.findByCep(dto.getCep());
		if (endereco != null) {
			funcionario.setEndereco(null);
		} else {
			RestTemplate rs = new RestTemplate();
			String uri = "https://viacep.com.br/ws/" + dto.getCep() + "/json/";
			Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				endereco = new Endereco();
				endereco.setCep(enderecoViaCep.get().getCep());
				endereco.setBairro(enderecoViaCep.get().getBairro());
				endereco.setLocalidade(enderecoViaCep.get().getLocalidade());
				endereco.setLogradouro(enderecoViaCep.get().getUf());
				enderecoRepository.save(null);
			} else {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}

		}
		funcionario = repository.save(funcionario);
		fotoService.inserir(funcionario, file);
		return ResponseEntity.noContent().build();
	}
	
	//
	//	METODO PRO GET FOTO
	//
	private FuncionarioResponseDTO adicionarUrlFoto(Funcionario funcionario) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/funcionarios/{id}/foto")
				.buildAndExpand(funcionario.getId()).toUri();

		FuncionarioResponseDTO dto = new FuncionarioResponseDTO();
		dto.setNome(funcionario.getNome());
		dto.setCargo(funcionario.getCargo());

		return dto;
	}

	//
	//	METODO PRO GET ID
	//
	public FuncionarioResponseDTO buscar(Long id) {
		Optional<Funcionario> funcionario = repository.findById(id);
		if (funcionario.isPresent()) {
			return new FuncionarioResponseDTO(funcionario.get());
		}
		throw new ResourceNotFoundException("Funcionario não encontrado");
	}
	
	//
	//	METODO PRO GET ALL
	//
	public List<FuncionarioResponseDTO> listar() {
		List<Funcionario> funcionarios = repository.findAll();
		List<FuncionarioResponseDTO> dtos = new ArrayList<>();
		for (Funcionario funcionario : funcionarios) {
			dtos.add(new FuncionarioResponseDTO(funcionario));
		}
		return dtos;
	}
}