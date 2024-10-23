package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Categoria;
import br.com.serratec.repository.CategoriaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	//Controller faz o meio de campo entre o Serviço e o FrontEnd
	@Autowired
	private CategoriaRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria inserir(@Valid @RequestBody Categoria c) {
		return repository.save(c);
	}

	/*@PostMapping("/varios")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Categoria> inserirVarios(@RequestBody List<Categoria> categorias) {
		return repository.saveAll(categorias);
	}*/

	@GetMapping
	public List<Categoria> listar() {
		return repository.findAll();
	}

	@PutMapping("{id}")
	public ResponseEntity<Categoria> alterarCategoria(@PathVariable Long id, @Valid @RequestBody Categoria c) {
		if (repository.existsById(id)) {
			c.setId(id); // 
			return ResponseEntity.ok(repository.save(c));
		}
		return ResponseEntity.notFound().build();	
	}	
}

