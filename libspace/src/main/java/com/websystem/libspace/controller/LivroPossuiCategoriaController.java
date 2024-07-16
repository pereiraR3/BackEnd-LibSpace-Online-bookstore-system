package com.websystem.libspace.controller;

import com.websystem.libspace.domain.livro_possui_categoria.LivroPossuiCategoriaResponseDTO;
import com.websystem.libspace.service.LivroPossuiCategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/livro_possui_categoria")
public class LivroPossuiCategoriaController {

    @Autowired
    private LivroPossuiCategoriaService livroPossuiCategoriaService;

    @Operation(
            summary = "Criação de uma nova relação entre livro e categoria.",
            description = "Cria uma nova relação entre um livro e uma categoria com base nos IDs fornecidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Relação livro-categoria criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("/{idLivro}/create_livro_possui_categoria/{idCategoria}")
    @Transactional
    public ResponseEntity<LivroPossuiCategoriaResponseDTO> create(@PathVariable Long idLivro, @PathVariable Long idCategoria, UriComponentsBuilder uriComponentsBuilder){

        LivroPossuiCategoriaResponseDTO livroPossuiCategoriaResponseDTO = livroPossuiCategoriaService.create(idLivro, idCategoria);

        var uri = uriComponentsBuilder.path("/{idLivro}/create_livro_possui_categoria/{idCategoria}")
                .buildAndExpand(livroPossuiCategoriaResponseDTO.id_livro(), livroPossuiCategoriaResponseDTO.id_categoria()).toUri();

        return ResponseEntity.created(uri).body(livroPossuiCategoriaResponseDTO);

    }

    @Operation(
            summary = "Listagem de categorias por livro.",
            description = "Lista todas as categorias associadas a um livro com base no ID do livro fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorias encontradas com sucesso."),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/categorias_por_livro/{idLivro}")
    public ResponseEntity<List<LivroPossuiCategoriaResponseDTO>> getById(@PathVariable Long idLivro){

        List<LivroPossuiCategoriaResponseDTO> livroPossuiCategoriaResponseDTOList = livroPossuiCategoriaService.findByLivroId(idLivro);

        return ResponseEntity.ok().body(livroPossuiCategoriaResponseDTOList);

    }

    @Operation(
            summary = "Listagem de livros por categoria.",
            description = "Lista todos os livros associados a uma categoria com base no ID da categoria fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/livros_por_categoria/{idCategoria}")
    public ResponseEntity<List<LivroPossuiCategoriaResponseDTO>> findAll(@PathVariable Long idCategoria){

        List<LivroPossuiCategoriaResponseDTO> livroPossuiCategoriaResponseDTOList = livroPossuiCategoriaService.findByCategoriaId(idCategoria);

        return ResponseEntity.ok().body(livroPossuiCategoriaResponseDTOList);

    }

    @Operation(
            summary = "Deleção de uma relação entre livro e categoria.",
            description = "Deleta uma relação entre um livro e uma categoria com base nos IDs fornecidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Relação deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Relação não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/delete/{idLivro}/relacao/{idCategoria}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long idLivro, @PathVariable Long idCategoria){

        livroPossuiCategoriaService.deleteByLivroIdByCategoriaId(idLivro, idCategoria);

        return ResponseEntity.noContent().build();

    }

}
