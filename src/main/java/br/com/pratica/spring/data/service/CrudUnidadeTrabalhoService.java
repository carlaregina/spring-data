package br.com.pratica.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.pratica.spring.data.orm.UnidadeTrabalho;
import br.com.pratica.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	private Boolean system = true;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao voce quer executar");
			System.out.println("0 - sair");
			System.out.println("1 - salvar");
			System.out.println("2 - atualizar");
			System.out.println("3 - visualizar");
			System.out.println("4 - deletar");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
			
		}
	}
	public void salvar(Scanner scanner) {
		
		System.out.println("Descricao da unidade de trabalho");
		String descricao = scanner.nextLine().toUpperCase();
		descricao += scanner.nextLine().toUpperCase();
		
		System.out.println("Digite o endereco");
		String endereco = scanner.nextLine().toUpperCase();
		endereco += scanner.nextLine().toUpperCase();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("salvo");
	}
	public void atualizar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		
		System.out.println("Descricao da unidade de trabalho");
		String descricao = scanner.nextLine().toUpperCase();
		descricao += scanner.nextLine().toUpperCase();
		
		System.out.println("Digite o endereco");
		String endereco = scanner.nextLine().toUpperCase();
		endereco += scanner.nextLine().toUpperCase();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Atualizado");
	}
	public void visualizar() {
		Iterable<UnidadeTrabalho> unidadeTrabalho = unidadeTrabalhoRepository.findAll();
		unidadeTrabalho.forEach(unidade -> System.out.println(unidade));
		
	}
	public void deletar(Scanner scanner) {
		
		System.out.println("Id");
		int id = scanner.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
	

}
