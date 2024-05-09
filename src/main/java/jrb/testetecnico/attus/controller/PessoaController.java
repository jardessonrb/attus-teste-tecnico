package jrb.testetecnico.attus.controller;

import jakarta.validation.Valid;
import jrb.testetecnico.attus.domain.dto.PessoaDto;
import jrb.testetecnico.attus.domain.form.EnderecoForm;
import jrb.testetecnico.attus.domain.form.PessoaForm;
import jrb.testetecnico.attus.service.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<Page<PessoaDto>> buscarPessoa(@RequestParam(required = false, name = "nome") String nome,
                                                        @RequestParam(required = false, name = "dataNascimento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataNascimento,
                                                        @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao){

        return ResponseEntity.ok(pessoaService.buscar(nome, dataNascimento, paginacao));
    }

    @PostMapping
    public ResponseEntity<PessoaDto> criarPessoa(@RequestBody @Valid PessoaForm pessoaForm, UriComponentsBuilder uriComponentsBuilder){
        PessoaDto pessoaDto = pessoaService.criar(pessoaForm);

        URI uri = uriComponentsBuilder.path("/pessoa/{id}").buildAndExpand(pessoaDto.getId()).toUri();

        return ResponseEntity.created(uri).body(pessoaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> buscarPessoaPorId(@PathVariable("id") UUID pessoaId){
        PessoaDto pessoaDto = pessoaService.buscarPorId(pessoaId);

        return ResponseEntity.ok(pessoaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto > editarPessoa(@PathVariable("id") UUID pessoaId, @RequestBody @Valid PessoaForm pessoaForm){

        return ResponseEntity.ok(pessoaService.editar(pessoaId, pessoaForm));
    }

    @PatchMapping("/{pessoaId}/endereco-principal/{enderecoId}")
    public ResponseEntity<PessoaDto > definirEnderecoPrincipal(@PathVariable("pessoaId") UUID pessoaId,
                                                   @PathVariable("enderecoId") UUID enderecoId){
        pessoaService.definirEnderecoPrincipal(pessoaId, enderecoId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{pessoaId}/inserir-endereco")
    public ResponseEntity<PessoaDto> inserirEndereco(@PathVariable("pessoaId") UUID pessoaId, @RequestBody EnderecoForm enderecoForm){
        PessoaDto pessoaDto = pessoaService.inserirEndereco(pessoaId, enderecoForm);

        return ResponseEntity.ok(pessoaDto);
    }


}
