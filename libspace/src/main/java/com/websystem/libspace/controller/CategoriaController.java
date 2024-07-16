package com.websystem.libspace.controller;

import com.websystem.libspace.domain.categoria.CategoriaRequestDTO;
import com.websystem.libspace.domain.categoria.CategoriaResponseDTO;
import com.websystem.libspace.domain.categoria.CategoriaUpdateDTO;
import com.websystem.libspace.service.CategoriaService;
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
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // EndPoint - "/categoria/create"
    @Operation(
            summary = "Criação de um novo registro do tipo categoria.",
            description = "Cria um novo registro de categoria com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<CategoriaResponseDTO> create(@RequestBody @Valid CategoriaRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        CategoriaResponseDTO categoriaResponseDTO = categoriaService.create(body);
        var uri = uriComponentsBuilder.path("/categoria/{id}").buildAndExpand(categoriaResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(categoriaResponseDTO);
    }

    // EndPoint - "/categoria/{id}"
    @Operation(
            summary = "Listagem de um único registro do tipo categoria.",
            description = "Lista um único registro de categoria com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable Long id){
        CategoriaResponseDTO categoriaResponseDTO = new CategoriaResponseDTO(categoriaService.findById(id));
        return ResponseEntity.ok().body(categoriaResponseDTO);
    }

    // EndPoint - "/categoria/findAll"
    @Operation(
            summary = "Listagem de todos os registros do tipo categoria.",
            description = "Listagem de todos os registro de categoria com base nas informações persistidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorias encontradas com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<CategoriaResponseDTO>> getAllCategoria(){
        List<CategoriaResponseDTO> categoriaResponseDTOList = categoriaService.findAll();
        return ResponseEntity.ok().body(categoriaResponseDTOList);
    }

    // EndPoint - "/categoria/update"
    @Operation(
            summary = "Atualização de um registro do tipo categoria.",
            description = "Atualiza um registro de categoria com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria atualizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid CategoriaUpdateDTO updateDTO){
        categoriaService.update(updateDTO);
        return ResponseEntity.noContent().build();
    }

    // EndPoint - "/categoria/{id}"
    @Operation(
            summary = "Deleção relacional de uma determinada categoria.",
            description = "Realiza a deleção relacional de uma categoria específica com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
