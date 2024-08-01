package br.com.fiap.techchallengeparking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fiap.techchallengeparking.model.Estacionamento;

public interface EstacionamentoRepository extends MongoRepository<Estacionamento, String> {
  
}
