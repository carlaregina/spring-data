package br.com.pratica.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.pratica.spring.data.orm.Funcionario;
import br.com.pratica.spring.data.repository.FuncionarioProjecao;
import br.com.pratica.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private Boolean system = true;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao voce quer executar");
			System.out.println("0 - sair");
			System.out.println("1 - Busca funcionario nome");
			System.out.println("2 - Busca funcionario pelo primeiro nome");
			System.out.println("3 - Busca funcionario pelo ultimo nome");
			System.out.println("4 - Busca funcionario pela descricao do cargo");
			System.out.println("5 - Busca funcionario pelo nome, data de contratacao e salario");
			System.out.println("6 - Busca funcionario per data contratacao");
			System.out.println("6 - Pesquisa funcionario salario");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			
			case 2:
				buscaFuncionarioPrefixo(scanner);
				break;
				
			case 3:
				buscaFuncionarioSufixo(scanner);
				break;
			case 4:
				buscaFuncionarioDescricaoDoCargo(scanner);
				break;
			case 5:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
				
			case 6:
				buscaFuncionarioDataContratacao(scanner);
				break;
				
			case 7:
				pesquisaFuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}
			
		}
	}
	private void buscaFuncionarioNome(Scanner scanner) {
		
		System.out.println("Qual nome deseja pesquisar: ");
		
		String nome = scanner.nextLine().toUpperCase();
		nome += scanner.nextLine().toUpperCase();
		
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
		
	}
	private void buscaFuncionarioPrefixo(Scanner scanner) {
		
		System.out.println("Qual nome deseja pesquisar: ");
		
		String nome = scanner.nextLine().toUpperCase();
		nome += scanner.nextLine().toUpperCase();
		List<Funcionario> list = funcionarioRepository.findByNomeStartingWith(nome);
		list.forEach(System.out::println);
		
	}
	private void buscaFuncionarioSufixo(Scanner scanner) {
		
		System.out.println("Qual nome deseja pesquisar: ");
		
		String nome = scanner.nextLine().toUpperCase();
		nome += scanner.nextLine().toUpperCase();
		List<Funcionario> list = funcionarioRepository.findByNomeEndingWith(nome);
		list.forEach(System.out::println);
		
	}
	private void buscaFuncionarioDescricaoDoCargo(Scanner scanner) {
		
		System.out.println("Qual nome deseja pesquisar: ");
		
		String descricao = scanner.nextLine().toUpperCase();
		descricao += scanner.nextLine().toUpperCase();
		List<Funcionario> list = funcionarioRepository.findByCargoDescricao(descricao);
		list.forEach(System.out::println);
		
	}
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		
		System.out.println("Qual nome deseja pesquisar: ");
		String nome = scanner.nextLine().toUpperCase();
		nome += scanner.nextLine().toUpperCase();
		
		System.out.println("Qual a data de contratacao: ");
		String data = scanner.nextLine().toUpperCase();
		data += scanner.nextLine().toUpperCase();
		LocalDate localdate = LocalDate.parse(data, formatter);
		
		System.out.println("Qual o salario: ");
		double salario = scanner.nextDouble();

		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataDeContratacao(nome, salario, localdate);
		list.forEach(System.out::println);	
	}
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		
		System.out.println("Qual a data de contratacao: ");
		String data = scanner.nextLine().toUpperCase();
		data += scanner.nextLine().toUpperCase();
		LocalDate localdate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localdate);
		list.forEach(System.out::println);	
	}
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario id: " + f.getId() + 
				" | Nome: " + f.getNome() + " | Salario: " + f.getSalario()));
	}
}
