package br.com.serratec.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.dto.LancamentoVendasRequestDTO;
import br.com.serratec.dto.LancamentoVendasResponseDTO;
import br.com.serratec.entity.LancamentoVendas;
import br.com.serratec.service.LancamentoVendasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoVendasController {
	@Autowired
	private LancamentoVendasService service;

	@Operation(summary = "Insere uma nova venda", description = "A resposta retorna a venda efetuada")
	@ApiResponses(value = { @ApiResponse(responseCode = "200",  content = {
			@Content(schema = @Schema(implementation = LancamentoVendas.class), mediaType = "application/json") }, description = "Lancamento de venda cadastrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping("{id}")
	public ResponseEntity<List<LancamentoVendasResponseDTO>> findById() {
		return ResponseEntity.ok(service.listar());
	}

	@Operation(summary = "Insere uma nova venda", description = "A resposta retorna a venda efetuada")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
			@Content(schema = @Schema(implementation = LancamentoVendas.class), mediaType = "application/json") }, description = "Lancamento de venda cadastrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody LancamentoVendas lancamentoVendas) {
		LancamentoVendasRequestDTO dto = service.inserir(lancamentoVendas);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(lancamentoVendas.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@Operation(summary = "Insere uma nova venda", description = "A resposta retorna a venda efetuada")
	@ApiResponses(value = { @ApiResponse(responseCode = "200",  content = {
			@Content(schema = @Schema(implementation = LancamentoVendas.class), mediaType = "application/json") }, description = "Lancamento de venda cadastrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping
	public ResponseEntity<List<LancamentoVendasResponseDTO>> findByVendedor() {
		return ResponseEntity.ok(service.listar());
	}
}
