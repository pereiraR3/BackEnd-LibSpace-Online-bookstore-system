package com.websystem.libspace.controller;

import com.websystem.libspace.domain.avaliacao.AvaliacaoRequestDTO;
import com.websystem.libspace.domain.avaliacao.AvaliacaoResponseDTO;
import com.websystem.libspace.domain.avaliacao.AvaliacaoUpdateDTO;
import com.websystem.libspace.service.AvaliacaoService;
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

public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    // EndPoint - "/avaliacao/create"
    @Operation(
            summary = "Criação de um novo registro do tipo avaliação.",
            description = "Cria um novo registro de avaliação com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Avaliação criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<AvaliacaoResponseDTO> create(@RequestBody @Valid AvaliacaoRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        AvaliacaoResponseDTO avaliacaoResponseDTO = avaliacaoService.create(body);
        var uri = uriComponentsBuilder.path("/create/{id}").buildAndExpand(avaliacaoResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(avaliacaoResponseDTO);
    }

    // EndPoint - "/avaliacao/{id}"
    @Operation(
            summary = "Listagem de um único registro do tipo avaliação.",
            description = "Lista um único registro de avaliação com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> getById(@PathVariable Long id){
        AvaliacaoResponseDTO avaliacaoResponseDTO = new AvaliacaoResponseDTO(avaliacaoService.findById(id));
        return ResponseEntity.ok().body(avaliacaoResponseDTO);
    }

    // EndPoint - "/avaliacao/findAll"
    @Operation(
            summary = "Listagem de todos os registros do tipo avaliação.",
            description = "Listagem de todos os registro de avaliação com base nas informações persistidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliações encontradas com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhuma avaliação encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<AvaliacaoResponseDTO>> getAllCategoria(){
        List<AvaliacaoResponseDTO> avaliacaoResponseDTOList = avaliacaoService.findAll();
        return ResponseEntity.ok().body(avaliacaoResponseDTOList);
    }

    // EndPoint - "/avaliacao/update"
    @Operation(
            summary = "Atualização de um registro do tipo avaliação.",
            description = "Atualiza um registro de avaliação com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Avaliação atualizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid AvaliacaoUpdateDTO updateDTO){
        avaliacaoService.update(updateDTO);
        return ResponseEntity.noContent().build();
    }

    // EndPoint - "/avaliacao/{id}"
    @Operation(
            summary = "Deleção relacional de uma determinada avaliação.",
            description = "Realiza a deleção relacional de uma avaliação específica com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Avaliação deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){
        avaliacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
