package br.com.fiap.techchallengeparking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fiap.techchallengeparking.model.Veiculo;

public interface VeiculoRepository extends MongoRepository<Veiculo, String> {
  
}
