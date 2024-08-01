package br.com.fiap.techchallengeparking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("pagamentos")
public class Pagamento {

  @Id
  private String id;
  private PagamentoTipo tipo;
  private String estacionamento_id;
  private Double valor;

  public Pagamento() {}

  public Pagamento(String id, PagamentoTipo tipo, String estacionamento_id, Double valor) {
    this.id = id;
    this.tipo = tipo;
    this.estacionamento_id = estacionamento_id;
    this.valor = valor;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PagamentoTipo getTipo() {
    return this.tipo;
  }

  public void setTipo(PagamentoTipo tipo) {
    this.tipo = tipo;
  }

  public String getEstacionamento_id() {
    return this.estacionamento_id;
  }

  public void setEstacionamento_id(String estacionamento_id) {
    this.estacionamento_id = estacionamento_id;
  }

  public Double getValor() {
    return this.valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  
}
