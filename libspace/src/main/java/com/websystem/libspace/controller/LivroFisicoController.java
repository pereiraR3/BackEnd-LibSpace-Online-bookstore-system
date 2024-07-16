package com.websystem.libspace.controller;

import com.websystem.libspace.domain.livro_fisico.LivroFisicoRequestDTO;
import com.websystem.libspace.domain.livro_fisico.LivroFisicoResponseDTO;
import com.websystem.libspace.domain.livro_fisico.LivroFisicoUpdateDTO;
import com.websystem.libspace.service.LivroFisicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/livro_fisico")
public class LivroFisicoController {

    @Autowired
    private LivroFisicoService livroFisicoService;

    @Operation(
            summary = "Criação de um novo registro do tipo livro físico.",
            description = "Cria um novo registro de livro físico com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro físico criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<LivroFisicoResponseDTO> create(@RequestBody @Valid LivroFisicoRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        LivroFisicoResponseDTO livroFisicoResponseDTO = livroFisicoService.create(body);

        var uri = uriComponentsBuilder.path("/livro_fisico/{id}").buildAndExpand(livroFisicoResponseDTO.id_livro()).toUri();

        return ResponseEntity.created(uri).body(livroFisicoResponseDTO);

    }

    @Operation(
            summary = "Listagem de um único registro do tipo livro físico.",
            description = "Lista um único registro de livro físico com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro físico encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Livro físico não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LivroFisicoResponseDTO> getById(@PathVariable Long id){

        LivroFisicoResponseDTO livroFisicoResponseDTO = new LivroFisicoResponseDTO(livroFisicoService.findById(id));

        return ResponseEntity.ok().body(livroFisicoResponseDTO);

    }

    @Operation(
            summary = "Listagem de todos os registros do tipo livro físico.",
            description = "Lista todos os registros de livro físico persistidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros físicos encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum livro físico encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<LivroFisicoResponseDTO>> findAll(){

        List<LivroFisicoResponseDTO> livroFisicoResponseDTOList = livroFisicoService.findAll();

        return ResponseEntity.ok().body(livroFisicoResponseDTOList);

    }

    @Operation(
            summary = "Atualização de um registro do tipo livro físico.",
            description = "Atualiza um registro de livro físico com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro físico atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "404", description = "Livro físico não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid LivroFisicoUpdateDTO body){

        livroFisicoService.update(body);

        return ResponseEntity.noContent().build();

    }

    @Operation(
            summary = "Deleção de um determinado livro físico.",
            description = "Realiza a deleção de um livro físico específico com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro físico deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Livro físico não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){

        livroFisicoService.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}
