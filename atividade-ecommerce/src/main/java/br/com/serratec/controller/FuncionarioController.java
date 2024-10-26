package br.com.serratec.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.dto.FuncionarioRequestDTO;
import br.com.serratec.dto.FuncionarioResponseDTO;
import br.com.serratec.entity.Foto;
import br.com.serratec.entity.Funcionario;
import br.com.serratec.service.FotoService;
import br.com.serratec.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	
	@Autowired
	private FotoService fotoService;
	
	//
	//	POST
	//
	@Operation(summary = "Insere um novo funcionário", description = "A resposta retorna o nome, email e telefone sem expor o id ou o cpf.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", 
			content = {@Content(schema = @Schema(implementation = Funcionario.class), mediaType = "application/json")},
			description = "Funcionário cadastrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			}
	)
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestPart FuncionarioRequestDTO dto, @RequestPart MultipartFile file)throws IOException {
		FuncionarioResponseDTO dtoResponse = service.inserir(dto, file);
		return ResponseEntity.created(null).body(dtoResponse);
	}
	
	//
	//	PUT
	//
	@Operation(summary = "Atualiza um funcionário existente", description = "Retorna os dados atualizados do funcionário.")
	@ApiResponses(value = { 
	    @ApiResponse(responseCode = "200", 
	        content = {@Content(schema = @Schema(implementation = Funcionario.class), mediaType = "application/json")},
	        description = "Funcionário atualizado com sucesso"),
	    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
	    @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
	    @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
	    @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
	    @ApiResponse(responseCode = "500", description = "Exceção interna da aplicação")
	})
	@PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestPart FuncionarioRequestDTO dto, @RequestPart MultipartFile file)throws IOException  {
		FuncionarioResponseDTO funcionarioAtualizado = service.atualizarFuncionario(id, dto, file);
        return ResponseEntity.ok(funcionarioAtualizado);
    }
	
	//
	//	DELETE
	//
	@Operation(summary = "Remove um funcionário", description = "Confirma a remoção do funcionário.")
	@ApiResponses(value = { 
	    @ApiResponse(responseCode = "204", description = "Funcionário removido com sucesso"),
	    @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
	    @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
	    @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
	    @ApiResponse(responseCode = "500", description = "Exceção interna da aplicação")
	})
	@DeleteMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> remover(@PathVariable Long id, @RequestPart FuncionarioRequestDTO dto, @RequestPart MultipartFile file)throws IOException  {
        return ResponseEntity.noContent().build();
    }
	
	//
	//	GET FOTO
	//
	@Operation(summary = "Exibe um funcionário e sua foto de identificação", description = "A resposta lista os dados do funcionário id, nome, cpf e email e sua foto.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", 
			content = {@Content(schema = @Schema(implementation = Funcionario.class), mediaType = "application/json")},
			description = "Retorna os dados do funcionário escolhido junto de sua foto"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping("{idFuncionario}/fotos/{idFoto}")
	public ResponseEntity<byte[]> buscarFuncionarioFotos(@PathVariable Long idFuncionario, @PathVariable Long idFoto) {
		Foto foto = fotoService.buscarFotoFuncionario(idFuncionario, idFoto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", foto.getTipo());
		headers.add("Content-length", String.valueOf(foto.getDados().length));
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
	}
	
	
	
	//
	//	GET ID
	//
	@Operation(summary = "Exibe um funcionário", description = "A resposta lista os dados do funcionário id, nome, cpf e email.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", 
			content = {@Content(schema = @Schema(implementation = Funcionario.class), mediaType = "application/json")},
			description = "Retorna os dados do funcionário escolhido"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping("{id}")
	public  ResponseEntity<FuncionarioResponseDTO> buscar(@PathVariable Long id){
		return ResponseEntity.ok(service.buscar(id));
	}
	
	//
	//	GET ALL
	//
	@Operation(summary = "Lista todos os funcionários", description = "A resposta lista os dados dos funcionários id, nome, cpf e email.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", 
			content = {@Content(schema = @Schema(implementation = Funcionario.class), mediaType = "application/json")},
			description = "Retorna todos os funcionários"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping
	public ResponseEntity<List<FuncionarioResponseDTO>> listar() {
		return ResponseEntity.ok(service.listar());
	}
}
