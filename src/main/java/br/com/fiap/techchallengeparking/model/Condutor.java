package br.com.fiap.techchallengeparking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("condutores")
public class Condutor {

  @Id
  private String id;
  private String nome;
  private String cpf;
  private String endereco;
  private String email;

  public Condutor() {}

  public Condutor(String id, String nome, String cpf, String endereco, String email) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.endereco = endereco;
    this.email = email;
  }



  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getEndereco() {
    return this.endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  
}
