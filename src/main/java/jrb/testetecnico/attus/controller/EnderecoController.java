package jrb.testetecnico.attus.controller;

import jakarta.validation.Valid;
import jrb.testetecnico.attus.domain.dto.EnderecoDto;
import jrb.testetecnico.attus.domain.form.EnderecoForm;
import jrb.testetecnico.attus.service.endereco.EnderecoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{enderecoId}")
    public ResponseEntity<EnderecoDto> buscarEnderecoPorId(@PathVariable("enderecoId") UUID enderecoId){
        EnderecoDto enderecoDto = enderecoService.buscarEnderecosPorId(enderecoId);

        return ResponseEntity.ok(enderecoDto);
    }

    @GetMapping("/por-pessoa/{pessoaId}")
    public ResponseEntity<List<EnderecoDto>> buscarEnderecosPorPessoa(@PathVariable("pessoaId") UUID pessoaId){
        List<EnderecoDto> enderecos = enderecoService.buscarEnderecosPorPessoa(pessoaId);

        return ResponseEntity.ok(enderecos);
    }

    @PutMapping("/{enderecoId}")
    public ResponseEntity<EnderecoDto> atualizarEndereco(@PathVariable("enderecoId") UUID enderecoId, @RequestBody @Valid  EnderecoForm enderecoForm){
        EnderecoDto enderecoDto = enderecoService.atualizar(enderecoId, enderecoForm);

        return ResponseEntity.ok(enderecoDto);
    }
}
