package br.com.serratec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.CarrinhoRequestDTO;
import br.com.serratec.dto.CarrinhoResponseDTO;
import br.com.serratec.entity.Carrinho;
import br.com.serratec.entity.Pedido;
import br.com.serratec.entity.Produto;
import br.com.serratec.repository.CarrinhoRepository;
import br.com.serratec.repository.ClienteRepository;
import br.com.serratec.repository.PedidoRepository;
import br.com.serratec.repository.ProdutoRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository repository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	//CRUDs
	
	// Inserir um novo carrinho (POST)
	public CarrinhoResponseDTO inserirCarrinho(CarrinhoRequestDTO requisicaoDTO, Long idPedido, Long idProduto) {
		var carrinho = new Carrinho();
		Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
		Optional<Produto> produto = produtoRepository.findById(idProduto);

		// adiciona as informações do cliente
		carrinho.setPedido(pedido.get());
		carrinho.setProduto(produto.get());
		
		carrinho = repository.save(carrinho);

		// retorna
		return new CarrinhoResponseDTO(carrinho);
	}
	
	

}
