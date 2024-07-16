package com.websystem.libspace.controller;

import com.websystem.libspace.domain.categoria.CategoriaRequestDTO;
import com.websystem.libspace.domain.categoria.CategoriaResponseDTO;
import com.websystem.libspace.domain.categoria.CategoriaUpdateDTO;
import com.websystem.libspace.domain.genero.GeneroRequestDTO;
import com.websystem.libspace.domain.genero.GeneroResponseDTO;
import com.websystem.libspace.domain.genero.GeneroUpdateDTO;
import com.websystem.libspace.service.CategoriaService;
import com.websystem.libspace.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    // EndPoint - "/genero/create"
    @Operation(
            summary = "Criação de um novo registro do tipo genêro.",
            description = "Cria um novo registro de genêro com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Genêro criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<GeneroResponseDTO> create(@RequestBody @Valid GeneroRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        GeneroResponseDTO generoResponseDTO = generoService.create(body);
        var uri = uriComponentsBuilder.path("/categoria/{id}").buildAndExpand(generoResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(generoResponseDTO);
    }

    // EndPoint - "/genero/{id}"
    @Operation(
            summary = "Listagem de um único registro do tipo genêro.",
            description = "Lista um único registro de genêro com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genêro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Genêro não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GeneroResponseDTO> getById(@PathVariable Long id){
        GeneroResponseDTO generoResponseDTO = new GeneroResponseDTO(generoService.findById(id));
        return ResponseEntity.ok().body(generoResponseDTO);
    }

    // EndPoint - "/genero/findAll"
    @Operation(
            summary = "Listagem de todos os registros do tipo genêro.",
            description = "Listagem de todos os registro de genêro com base nas informações persistidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genêros encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhuma genêro encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<GeneroResponseDTO>> getAllCategoria(){
        List<GeneroResponseDTO> generoResponseDTOList = generoService.findAll();
        return ResponseEntity.ok().body(generoResponseDTOList);
    }

    // EndPoint - "/genero/update"
    @Operation(
            summary = "Atualização de um registro do tipo genêro.",
            description = "Atualiza um registro de genêro com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Genêro atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "404", description = "Genêro não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid GeneroUpdateDTO updateDTO){
        generoService.update(updateDTO);
        return ResponseEntity.noContent().build();
    }

    // EndPoint - "/genero/{id}"
    @Operation(
            summary = "Deleção relacional de uma determinada genêro.",
            description = "Realiza a deleção relacional de uma categoria específica com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Genêro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Genêro não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){
        generoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
