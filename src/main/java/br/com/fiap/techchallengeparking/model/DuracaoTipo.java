package br.com.fiap.techchallengeparking.model;

public enum DuracaoTipo {
  FIXO_30MINUTOS(2.5),
  FIXO_01HORA(4.0),
  FIXO_06HORAS(20.0),
  VARIAVEL(3.0);

  private Double valorTicket;


  DuracaoTipo(Double valorTicket) {
    this.valorTicket = valorTicket;
  }
  
  public Double getValor() {
    return this.valorTicket;
  }
  
}
