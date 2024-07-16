package com.websystem.libspace.controller;

import com.websystem.libspace.domain.livro_possui_genero.LivroPossuiGeneroResponseDTO;
import com.websystem.libspace.service.LivroPossuiGeneroService;
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
@RequestMapping("/livro_possui_genero")
public class LivroPossuiGeneroController {

    @Autowired
    private LivroPossuiGeneroService livroPossuiGeneroService;

    @Operation(
            summary = "Criação de uma nova relação entre livro e genêro.",
            description = "Cria uma nova relação entre um livro e uma genêro com base nos IDs fornecidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Relação livro-genero criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("/{idLivro}/create_livro_possui_genero/{idGenero}")
    @Transactional
    public ResponseEntity<LivroPossuiGeneroResponseDTO> create(@PathVariable Long idLivro, @PathVariable Long idGenero, UriComponentsBuilder uriComponentsBuilder){

        LivroPossuiGeneroResponseDTO livroPossuiGeneroResponseDTO = livroPossuiGeneroService.create(idLivro, idGenero);

        var uri = uriComponentsBuilder.path("/{idLivro}/create_livro_possui_categoria/{idCategoria}")
                .buildAndExpand(livroPossuiGeneroResponseDTO.id_livro(), livroPossuiGeneroResponseDTO.id_genero()).toUri();

        return ResponseEntity.created(uri).body(livroPossuiGeneroResponseDTO);

    }

    @Operation(
            summary = "Listagem de genêros por livro.",
            description = "Lista todas as genêros associadas a um livro com base no ID do livro fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genêros encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/generos_por_livro/{idLivro}")
    public ResponseEntity<List<LivroPossuiGeneroResponseDTO>> getById(@PathVariable Long idLivro){

        List<LivroPossuiGeneroResponseDTO> livroPossuiGeneroResponseDTOList = livroPossuiGeneroService.findByLivroId(idLivro);

        return ResponseEntity.ok().body(livroPossuiGeneroResponseDTOList);

    }

    @Operation(
            summary = "Listagem de livros por genêro.",
            description = "Lista todos os livros associados a uma genêro com base no ID do genêro fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Genêro não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/livros_por_genero/{idGenero}")
    public ResponseEntity<List<LivroPossuiGeneroResponseDTO>> findAll(@PathVariable Long idGenero){

        List<LivroPossuiGeneroResponseDTO> livroPossuiGeneroResponseDTOList = livroPossuiGeneroService.findByCategoriaId(idGenero);

        return ResponseEntity.ok().body(livroPossuiGeneroResponseDTOList);

    }

    @Operation(
            summary = "Deleção de uma relação entre livro e genêro.",
            description = "Deleta uma relação entre um livro e um genêro com base nos IDs fornecidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Relação deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Relação não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/delete/{idLivro}/relacao/{idGenero}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long idLivro, @PathVariable Long idGenero){

        livroPossuiGeneroService.deleteByLivroIdByGeneroId(idLivro, idGenero);

        return ResponseEntity.noContent().build();

    }

}
