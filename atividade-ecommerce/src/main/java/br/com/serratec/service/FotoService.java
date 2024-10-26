package br.com.serratec.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.entity.Foto;
import br.com.serratec.entity.Funcionario;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.FotoRepository;

@Service
public class FotoService {
	@Autowired
	private FotoRepository repository;

	public Foto inserir(Funcionario funcionario, MultipartFile file) throws IOException {
		Foto foto = new Foto();
		foto.setDados(file.getBytes());
		foto.setNome(file.getName());
		foto.setTipo(file.getContentType());
		foto.setFuncionario(funcionario);
		return repository.save(foto);
	}

	public Foto buscarPorIdFuncionario(Long id) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		Optional<Foto> foto = repository.findByFuncionario(funcionario);
		if (foto.isPresent()) {
			return foto.get();
		}
		throw new ResourceNotFoundException("Funcionario não encontrado");
	}

	public Foto buscarFotoFuncionario(Long idFuncionario, Long idFoto) {
		return repository.buscarFotoFuncionario(idFuncionario, idFoto);
	}

}