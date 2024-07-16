package com.websystem.libspace.controller;

import com.websystem.libspace.domain.editora.EditoraRequestDTO;
import com.websystem.libspace.domain.editora.EditoraResponseDTO;
import com.websystem.libspace.domain.editora.EditoraUpdateDTO;
import com.websystem.libspace.service.EditoraService;
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
@RequestMapping("/editora")
public class EditoraController {

    @Autowired
    private EditoraService editoraService;

    @Operation(
            summary = "Criação de um novo registro do tipo editora.",
            description = "Cria um novo registro de editora com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Editora criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<EditoraResponseDTO> create(@RequestBody @Valid EditoraRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        EditoraResponseDTO editoraResponseDTO = editoraService.create(body);

        var uri = uriComponentsBuilder.path("/editora/{id}").buildAndExpand(editoraResponseDTO.id()).toUri();

        return ResponseEntity.created(uri).body(editoraResponseDTO);

    }

    @Operation(
            summary = "Listagem de um único registro do tipo editora.",
            description = "Lista um único registro de editora com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editora encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Editora não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EditoraResponseDTO> getById(@PathVariable Long id){

        EditoraResponseDTO editoraResponseDTO = new EditoraResponseDTO(editoraService.findById(id));

        return ResponseEntity.ok().body(editoraResponseDTO);

    }

    @Operation(
            summary = "Listagem de todos os registros do tipo editora.",
            description = "Lista todos os registros de editora persistidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editoras encontradas com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhuma editora encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<EditoraResponseDTO>> getAllEditora(){

        List<EditoraResponseDTO> editoraResponseDTOList = editoraService.findAll();

        return ResponseEntity.ok().body(editoraResponseDTOList);

    }

    @Operation(
            summary = "Atualização de um registro do tipo editora.",
            description = "Atualiza um registro de editora com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Editora atualizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "404", description = "Editora não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid EditoraUpdateDTO updateDTO){

        editoraService.update(updateDTO);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Deleção de uma determinada editora.",
            description = "Realiza a deleção de uma editora específica com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Editora deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Editora não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){

        editoraService.deleteById(id);

        return ResponseEntity.noContent().build();

    }
}
