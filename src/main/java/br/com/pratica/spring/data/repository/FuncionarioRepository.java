package br.com.pratica.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.pratica.spring.data.orm.Funcionario;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
	//dando poder ao repository para fazer specifications
	JpaSpecificationExecutor<Funcionario>{
	
	List<Funcionario> findByNome(String nome);
	List<Funcionario> findByNomeStartingWith(String nome);
	List<Funcionario> findByNomeEndingWith(String nome);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario AND f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataDeContratacao(String nome, Double salario, LocalDate data);
	
	//descricao que eh atributo de Cargo
	List<Funcionario> findByCargoDescricao(String descricao);
	//analogo para este codigo abaixo
	//@Query("SELECT f FROM Funcionario f JOIN f.cargo c WHERE c.descricao = :descricao")
	//List<Funcionario> findByCargoPelaDescricao(String descricao);
	
	//para evitar um codigo mto verboso, vamos fazer uma jpql acima para dminuir a escrita, mas esta correto o modo abaixo
	List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String nome, Double salario, LocalDate data);

	
	@Query("SELECT f FROM Funcionario f JOIN f.unidadeTrabalhos u WHERE u.descricao = :descricao")
	List<Funcionario> findByUnidadeTrabalhos_Descricao(String descricao);
	
	@Query (value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data",
			nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	//novo método com paginação
    List<Funcionario> findByNome(String nome, Pageable pageable);
    
    @Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f",
    		nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario ();
	
	
	
}
