package br.com.pratica.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pratica.spring.data.orm.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer>{

}
