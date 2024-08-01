package br.com.fiap.techchallengeparking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fiap.techchallengeparking.model.Pagamento;

public interface PagamentoRepository extends MongoRepository<Pagamento, String>{
  
}
