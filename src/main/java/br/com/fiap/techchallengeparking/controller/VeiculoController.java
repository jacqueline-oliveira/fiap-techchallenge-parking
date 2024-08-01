package br.com.fiap.techchallengeparking.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.techchallengeparking.dtos.VeiculoDTO;
import br.com.fiap.techchallengeparking.service.VeiculoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

  @Autowired
  private VeiculoService servico;

  @GetMapping
  public ResponseEntity<List<VeiculoDTO>> obterVeiculos() {
    return ResponseEntity.ok(servico.obterTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<VeiculoDTO> obterveiculoPorId(@PathVariable String id) {
    Optional<VeiculoDTO> veiculo = servico.obterPorId(id);

    if (!veiculo.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(veiculo.get());
  }

  @PostMapping()
  public ResponseEntity<VeiculoDTO> cadastrarVeiculo(@RequestBody @Valid VeiculoDTO dto,
      UriComponentsBuilder uriBuilder) {
    VeiculoDTO veiculo = servico.cadastrarVeiculo(dto);
    URI endpoint = uriBuilder.path("/veiculos/{id}").buildAndExpand(veiculo.id()).toUri();
    return ResponseEntity.created(endpoint).body(veiculo);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> excluirVeiculo(@PathVariable String id) {
    servico.excluirPorId(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable String id, @RequestBody @Valid VeiculoDTO dto) {
    Optional<VeiculoDTO> veiculo = servico.atualizarPorId(id, dto);
    
    if (!veiculo.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(veiculo.get());
  }
  
}
