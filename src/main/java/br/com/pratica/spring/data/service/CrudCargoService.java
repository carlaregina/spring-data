package br.com.pratica.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.pratica.spring.data.orm.Cargo;
import br.com.pratica.spring.data.repository.CargoRepository;


//classe que vai criar as insercoes no bd
//nao eh possivel dar new em um interface para poder intanciar a classe cargorepository
//entao ela eh implemntada fazendo injecao de dependencia
@Service
public class CrudCargoService {
	
	private Boolean system = true;
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	// cliente vai dizer quais as funcoes que ele deseja executar dentro da calsse de cargo
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
		System.out.println("Descricao do cargo");
		String descricao = scanner.nextLine().toUpperCase();
		descricao += scanner.nextLine().toUpperCase();
		
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("salvo");
	}
	public void atualizar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		System.out.println("Descricao do Cargo");
		String descricao = scanner.nextLine().toUpperCase();
		descricao += scanner.nextLine().toUpperCase();
		
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Atualizado");
	}
	public void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
		
	}
	public void deletar(Scanner scanner) {
		
		System.out.println("Id");
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Deletado");
	}
}
