package br.com.pratica.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.pratica.spring.data.orm.Funcionario;
import br.com.pratica.spring.data.repository.FuncionarioRepository;
import br.com.pratica.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {

	private final FuncionarioRepository funcionarioRepository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		
		System.out.println("Digite um nome");
		String nome = scanner.nextLine().toUpperCase();
		nome += scanner.nextLine().toUpperCase();
		
		if(nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
		
		System.out.println("Digite um cpf");
		String cpf = scanner.nextLine().toUpperCase();
		cpf += scanner.nextLine().toUpperCase();
		
		if(cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("Digite um salario");
		Double salario = scanner.nextDouble();
		
		if(salario == 0){
			salario = null;
		}
		System.out.println("Digite um data de contratacao");
		
		String data = scanner.nextLine().toUpperCase();
		data += scanner.nextLine().toUpperCase();
		
		LocalDate dataContratacao;
		if(data.equalsIgnoreCase("NULL")){
			dataContratacao = null;
		} else {
			
		    dataContratacao = LocalDate.parse(data, formatter);
		}
			
		List<Funcionario> funcionarios = funcionarioRepository
				.findAll(Specification.where(SpecificationFuncionario.nome(nome))
						.or(SpecificationFuncionario.cpf(cpf))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacao))
						);
		funcionarios.forEach(System.out::println);
	
	}
}

