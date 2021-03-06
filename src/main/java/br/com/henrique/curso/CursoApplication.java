package br.com.henrique.curso;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.henrique.curso.domain.Categoria;
import br.com.henrique.curso.domain.Cidade;
import br.com.henrique.curso.domain.Cliente;
import br.com.henrique.curso.domain.Endereco;
import br.com.henrique.curso.domain.Estado;
import br.com.henrique.curso.domain.Pagamento;
import br.com.henrique.curso.domain.PagamentoComBoleto;
import br.com.henrique.curso.domain.PagamentoComCartao;
import br.com.henrique.curso.domain.Pedido;
import br.com.henrique.curso.domain.Produto;
import br.com.henrique.curso.enums.EstadoPagamento;
import br.com.henrique.curso.enums.TipoCliente;
import br.com.henrique.curso.repositories.CategoriaRepository;
import br.com.henrique.curso.repositories.CidadeRepository;
import br.com.henrique.curso.repositories.ClienteRepository;
import br.com.henrique.curso.repositories.EnderecoRepository;
import br.com.henrique.curso.repositories.EstadoRepository;
import br.com.henrique.curso.repositories.PagamentoRepository;
import br.com.henrique.curso.repositories.PedidoRepository;
import br.com.henrique.curso.repositories.ProdutoRepository;


@SpringBootApplication
public class CursoApplication implements CommandLineRunner {
	
	@Autowired private CategoriaRepository categoriaRepository;
	@Autowired private ProdutoRepository produtoRepository;
	
	@Autowired private EstadoRepository estadoRepository;
	@Autowired private CidadeRepository cidadeRepository;
	
	@Autowired private ClienteRepository clienteRepository;
	@Autowired private EnderecoRepository enderecoRepository;
	
	@Autowired private PedidoRepository pedidoRepository;
	@Autowired private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("61123456789", "61981926875"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "72161003", cli1, c1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "36525398", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1 );
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2 );
		
		Pagamento pagt1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagt1);		
		Pagamento pagt2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagt2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagt1, pagt2));
	}

}
