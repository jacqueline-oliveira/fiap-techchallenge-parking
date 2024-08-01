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

import br.com.fiap.techchallengeparking.dtos.CondutorDTO;
import br.com.fiap.techchallengeparking.service.CondutorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/condutores")
public class CondutorController {
   @Autowired
  private CondutorService servico;

  @GetMapping
  public ResponseEntity<List<CondutorDTO>> obterCondutores() {
    return ResponseEntity.ok(servico.obterTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CondutorDTO> obterCondutorPorId(@PathVariable String id) {
    Optional<CondutorDTO> condutor = servico.obterPorId(id);

    if (!condutor.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(condutor.get());
  }

  @PostMapping()
  public ResponseEntity<CondutorDTO> cadastrarCondutor(@RequestBody @Valid CondutorDTO dto,
      UriComponentsBuilder uriBuilder) {
    CondutorDTO condutor = servico.cadastrarCondutor(dto);
    URI endpoint = uriBuilder.path("/condutores/{id}").buildAndExpand(condutor.id()).toUri();
    return ResponseEntity.created(endpoint).body(condutor);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> excluirCondutor(@PathVariable String id) {
    servico.excluirPorId(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<CondutorDTO> atualizarCondutor(@PathVariable String id, @RequestBody @Valid CondutorDTO dto) {
    Optional<CondutorDTO> condutor = servico.atualizarPorId(id, dto);
    
    if (!condutor.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(condutor.get());
  }
 
}
