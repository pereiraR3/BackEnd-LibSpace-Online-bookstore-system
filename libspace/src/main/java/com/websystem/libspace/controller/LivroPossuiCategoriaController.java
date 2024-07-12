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

    @PostMapping("/{idLivro}/create_livro_possui_categoria/{idCategoria}")
    @Transactional
    public ResponseEntity<LivroPossuiCategoriaResponseDTO> create(@PathVariable Long idLivro, @PathVariable Long idCategoria, UriComponentsBuilder uriComponentsBuilder){

        LivroPossuiCategoriaResponseDTO livroPossuiCategoriaResponseDTO = livroPossuiCategoriaService.create(idLivro, idCategoria);

        var uri = uriComponentsBuilder.path("/{idLivro/create_livro_possui_categoria/{idCategoria}")
                .buildAndExpand(livroPossuiCategoriaResponseDTO.id_livro(), livroPossuiCategoriaResponseDTO.id_categoria()).toUri();

        return ResponseEntity.created(uri).body(livroPossuiCategoriaResponseDTO);

    }

    @GetMapping("/categorias_por_livro/{idLivro}")
    public ResponseEntity<List<LivroPossuiCategoriaResponseDTO>> getById(@PathVariable Long idLivro){

        List<LivroPossuiCategoriaResponseDTO> livroPossuiCategoriaResponseDTOList = livroPossuiCategoriaService.findByLivroId(idLivro);

        return ResponseEntity.ok().body(livroPossuiCategoriaResponseDTOList);

    }

    @GetMapping("/livros_por_categoria/{idCategoria}")
    public ResponseEntity<List<LivroPossuiCategoriaResponseDTO>> findAll(@PathVariable Long idCategoria){

        List<LivroPossuiCategoriaResponseDTO> livroPossuiCategoriaResponseDTOList = livroPossuiCategoriaService.findByCategoriaId(idCategoria);

        return ResponseEntity.ok().body(livroPossuiCategoriaResponseDTOList);

    }


    @DeleteMapping("/delete/{idLivro}/relacao/{idCategoria}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long idLivro, @PathVariable Long idCategoria){

        livroPossuiCategoriaService.deleteByLivroIdByCategoriaId(idLivro, idCategoria);

        return ResponseEntity.noContent().build();

    }

}
