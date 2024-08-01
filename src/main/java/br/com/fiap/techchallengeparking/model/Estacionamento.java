package br.com.fiap.techchallengeparking.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("estacionamento")
public class Estacionamento {

  @Id
  private String id;
  private Condutor condutor;
  private Veiculo veiculo;
  private DuracaoTipo duracaoTipo;
  private LocalDateTime inicio;
  private LocalDateTime fim;

  public Estacionamento() {}


  public Estacionamento(String id, Condutor condutor, Veiculo veiculo, DuracaoTipo duracaoTipo, LocalDateTime inicio, LocalDateTime fim) {
    this.id = id;
    this.condutor = condutor;
    this.veiculo = veiculo;
    this.duracaoTipo = duracaoTipo;
    this.inicio = inicio;
    this.fim = fim;
  }



  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Condutor getCondutor() {
    return this.condutor;
  }

  public void setCondutor(Condutor condutor) {
    this.condutor = condutor;
  }

  public Veiculo getVeiculo() {
    return this.veiculo;
  }

  public void setVeiculo(Veiculo veiculo) {
    this.veiculo = veiculo;
  }

  public DuracaoTipo getDuracaoTipo() {
    return this.duracaoTipo;
  }

  public void setDuracaoTipo(DuracaoTipo duracaoTipo) {
    this.duracaoTipo = duracaoTipo;
  }

  public LocalDateTime getInicio() {
    return this.inicio;
  }

  public void setInicio(LocalDateTime inicio) {
    this.inicio = inicio;
  }

  public LocalDateTime getFim() {
    return this.fim;
  }

  public void setFim(LocalDateTime fim) {
    this.fim = fim;
  } 
}
