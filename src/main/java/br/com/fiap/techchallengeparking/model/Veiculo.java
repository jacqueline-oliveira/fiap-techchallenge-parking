package br.com.fiap.techchallengeparking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("veiculos")
public class Veiculo {

  @Id
  private String id;
  private String placa;
  private String marca;
  private String modelo;

  public Veiculo() {}

  public Veiculo(String id, String placa, String marca, String modelo) {
    this.id = id;
    this.placa = placa;
    this.marca = marca;
    this.modelo = modelo;
  }
  

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPlaca() {
    return this.placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public String getMarca() {
    return this.marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getModelo() {
    return this.modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  
}
