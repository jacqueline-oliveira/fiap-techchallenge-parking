package br.com.fiap.techchallengeparking.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.techchallengeparking.dtos.EstacionamentoDTO;
import br.com.fiap.techchallengeparking.dtos.EstacionamentoInputDTO;
import br.com.fiap.techchallengeparking.service.EstacionamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

  
  @Autowired
  private EstacionamentoService servico;

  @GetMapping
  public ResponseEntity<List<EstacionamentoDTO>> obterDadosEstacionamento() {
    return ResponseEntity.ok(servico.obterTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<EstacionamentoDTO> obterRegistroPorId(@PathVariable String id) {
    Optional<EstacionamentoDTO> estacionamento = servico.obterPorId(id);

    if (!estacionamento.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(estacionamento.get());
  }

  @PostMapping("/iniciar")
  public ResponseEntity<EstacionamentoDTO> iniciarEstacionamento(@RequestBody @Valid EstacionamentoInputDTO dto,
      UriComponentsBuilder uriBuilder) {
    EstacionamentoDTO estacionamento = servico.iniciarEstacionamento(dto);
    URI endpoint = uriBuilder.path("/estacionamento/{id}").buildAndExpand(estacionamento.id()).toUri();
    return ResponseEntity.created(endpoint).body(estacionamento);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> excluirEstacionamento(@PathVariable String id) {
    servico.obterPorId(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<EstacionamentoDTO> atualizarEstacionamento(@PathVariable String id, @RequestBody @Valid EstacionamentoDTO dto) {
    Optional<EstacionamentoDTO> estacionamento = servico.atualizarPorId(id, dto);
    
    if (!estacionamento.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(estacionamento.get());
  }

   @PatchMapping("/{id}/finalizar")
  public ResponseEntity<EstacionamentoDTO> finalizarEstacionamento(@PathVariable String id,
      UriComponentsBuilder uriBuilder) {
    EstacionamentoDTO estacionamento = servico.finalizarEstacionamento(id);
    URI endpoint = uriBuilder.path("/estacionamento/{id}").buildAndExpand(estacionamento.id()).toUri();
    return ResponseEntity.created(endpoint).body(estacionamento);
  }
  
}
