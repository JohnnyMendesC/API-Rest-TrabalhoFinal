package br.com.serratec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.CategoriaRequestDTO;
import br.com.serratec.dto.CategoriaResponseDTO;
import br.com.serratec.entity.Categoria;
import br.com.serratec.repository.CategoriaRepository;

@Service
public class ClienteService {
//	REPOSITÓRIOS
	@Autowired
	private CategoriaRepository repository;

//	REGRAS DE NEGÓCIO
	// GET ALL | LISTAR TODOS
	public List<CategoriaResponseDTO> findAll() {

		List<Categoria> categorias = repository.findAll();
		List<CategoriaResponseDTO> respostasDTO = new ArrayList<CategoriaResponseDTO>();

		for (Categoria resposta : categorias) {
			respostasDTO.add(new CategoriaResponseDTO(resposta));
		}
		return respostasDTO;
	}
	// GET ID | LISTAR POR ID
	/*
	 * @GetMapping("{id}") public ResponseEntity<Item> exibirItem(@PathVariable Long
	 * id)
	 */

	// POST | INSERIR
	public CategoriaResponseDTO inserir(CategoriaRequestDTO requisicaoDTO) {
		// 1ºBUSCA EXCEPTIONS
		// if (!user.getSenha().equalsIgnoreCase(user.getConfirmaSenha())) {throw new
		// SenhaException("Senha e Confirma Senha não são iguais");}
		// if (usuarioRepository.findByEmail(user.getEmail())!=null) {throw new
		// EmailException("Email já existente");}
		// 2ºCRIA O ATRIBUTO QUE RECEBERÁ OS DADOS
		Categoria categoria = new Categoria();
		// 3ºINSTANCIA O ATRIBUTO COM OS DADOS DA REQUISIÇÃO
//		categoria.setX(requisicaoDTO.getX());
//		categoria.setY(requisicaoDTO.getY());
//		categoria.setZ(requisicaoDTO.getZ());
		// 4ºSALVA NO BANCO COM TODOS OS DADOS INSERIDOS
		repository.save(categoria);
		// 5ºRETORNA NO FRONT OS DADOS FILTRADOS COM O CONSTRUTOR DE responseDTO
		return new CategoriaResponseDTO(categoria);
	}

	// POST GROUP | POSTAR VÁRIOS
	/*
	 * @PostMapping("/varios") criarVariosItens(@RequestBody List<Item> itens) {
	 * return itemRepository.saveAll(itens);
	 */

	// PUT | ALTERAR
	/*
	 * @PutMapping("{id}") public ResponseEntity<Item> alterarItem(@PathVariable
	 * Long id, @RequestBody Item itemModificado) {
	 */

	// DELETE | DELETAR
	/*
	 * @DeleteMapping("{id}") public ResponseEntity<Void> apagarItem(@PathVariable
	 * Long id)
	 */
}
