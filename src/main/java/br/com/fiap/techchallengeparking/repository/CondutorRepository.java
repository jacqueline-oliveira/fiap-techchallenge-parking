package br.com.fiap.techchallengeparking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fiap.techchallengeparking.model.Condutor;

public interface CondutorRepository extends MongoRepository<Condutor, String> {
  
}
