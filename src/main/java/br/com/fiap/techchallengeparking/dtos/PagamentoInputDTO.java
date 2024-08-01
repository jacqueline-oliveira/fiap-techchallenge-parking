package br.com.fiap.techchallengeparking.dtos;

import br.com.fiap.techchallengeparking.model.PagamentoTipo;

public record PagamentoInputDTO(PagamentoTipo tipo, 
                          String estacionamento_id)
 {
  
}
