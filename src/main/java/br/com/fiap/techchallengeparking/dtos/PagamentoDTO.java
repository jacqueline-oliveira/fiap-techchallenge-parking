package br.com.fiap.techchallengeparking.dtos;

import br.com.fiap.techchallengeparking.model.Pagamento;
import br.com.fiap.techchallengeparking.model.PagamentoTipo;

public record PagamentoDTO(String id, 
                          PagamentoTipo tipo, 
                          String estacionamento_id, 
                          Double valor) {

  public PagamentoDTO(Pagamento p) {
    this(p.getId(), p.getTipo(), p.getEstacionamento_id(), p.getValor());
  }

  public Pagamento mapToPagamento() {
    return new Pagamento(this.id, this.tipo, this.estacionamento_id, this.valor);
  }


  
}
