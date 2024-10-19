package br.com.serratec.service;

import org.springframework.stereotype.Service;

@Service
public class MockService {
	/*
// REPOSITÓRIOS
	 * 
	 * @Autowired private MockRepository repository;
// REGRAS DE NEGÓCIO 
	 * Listar todos, com filtro do
	 * dto: O service faz o meio de campo entre Repositório e Controller Por isso o
	 * retorno é uma Lista do tipo <RespostaDTO> publica
	 * 
	 * 1º ele busca todos dados no repositório usando o método findAll e os salva
	 * num atributo Lista<Objeto> objetoS
	 * 
	 * 2º ele instancia o novo "Lista<RespostaDTO> respostasDTO;" que "emprestará" o
	 * seu construtor que já vem com as informações filtradas dos atributos certos
	 * 
	 * 3º ele então busca no Repositório base a informação primária daquele dado com
	 * todos os atributos direto da Entidade, e utiliza o
	 * respostasDTO.add(RespostaDTO(resposta)); para popular a lista respostasDTO
	 * com cada resposta do for, mesmo a resposta sendo do tipo Objeto resposta, o
	 * respostaDTO só irá receber os dados que batem com o seu contrutor, ignorando
	 * o resto
	 * 
	 * Por fim ele retorna o respostasDTO com os dados tratados
// GET ALL | LISTAR TODOS 
	 * public List<CategoriaResponseDTO> findAll() {
	 * 
	 * List<Categoria> categorias = repository.findAll(); List<CategoriaResponseDTO>
	 * respostasDTO = new ArrayList<CategoriaResponseDTO>();
	 * 
	 * for (Categoria resposta : categorias) { respostasDTO.add(new
	 * CategoriaResponseDTO(resposta)); } return respostasDTO; }
	 * 
// GET ID | LISTAR POR ID
	 * @GetMapping("{id}") 
	 * public ResponseEntity<Item> exibirItem(@PathVariable Long
	 * id) { Optional<Item> itemOptional = itemRepository.findById(id); if
	 * (itemOptional.isEmpty()) { return ResponseEntity.notFound().build(); } return
	 * ResponseEntity.ok(itemOptional.get()); }
	 * 
// POST | INSERIR 
	 * public CategoriaResponseDTO inserir(CategoriaRequestDTO
	 * requisicaoDTO) { // 1ºBUSCA EXCEPTIONS // if
	 * (!user.getSenha().equalsIgnoreCase(user.getConfirmaSenha())) {throw new //
	 * SenhaException("Senha e Confirma Senha não são iguais");} // if
	 * (usuarioRepository.findByEmail(user.getEmail())!=null) {throw new //
	 * EmailException("Email já existente");} // 2ºCRIA O ATRIBUTO QUE RECEBERÁ OS
	 * DADOS Categoria categoria = new Categoria(); // 3ºINSTANCIA O ATRIBUTO COM OS
	 * DADOS DA REQUISIÇÃO categoria.setX(requisicaoDTO.getX());
	 * categoria.setY(requisicaoDTO.getY()); categoria.setZ(requisicaoDTO.getZ());
	 * // 4ºSALVA NO BANCO COM TODOS OS DADOS INSERIDOS repository.save(categoria);
	 * // 5ºRETORNA NO FRONT OS DADOS FILTRADOS COM O CONSTRUTOR DE responseDTO
	 * return new CategoriaResponseDTO(categoria); }
	 * 
// POST GROUP | POSTAR VÁRIOS /*
	 * @PostMapping("/varios") criarVariosItens(@RequestBody List<Item> itens) {
	 * return itemRepository.saveAll(itens);
	 * 
// PUT | ALTERAR
	 * @PutMapping("{id}") public ResponseEntity<Item> alterarItem(@PathVariable
	 * Long id, @RequestBody Item itemModificado) { if
	 * (itemRepository.existsById(id)) { itemModificado.setId(id); //aqui ele vai
	 * fazer o put se não ele iria criar outro novo return
	 * ResponseEntity.ok(itemRepository.save(itemModificado)); } return
	 * ResponseEntity.notFound().build(); }
	 * 
// DELETE | DELETAR
	 * @DeleteMapping("{id}") public ResponseEntity<Void> apagarItem(@PathVariable
	 * Long id) { if (itemRepository.existsById(id)) {
	 * itemRepository.deleteById(id); return ResponseEntity.noContent().build(); }
	 * return ResponseEntity.notFound().build(); }
	 */
}
