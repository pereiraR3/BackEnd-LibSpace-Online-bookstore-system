package com.websystem.libspace.controller;

import com.websystem.libspace.domain.livro_ebook.LivroEbookRequestDTO;
import com.websystem.libspace.domain.livro_ebook.LivroEbookResponseDTO;
import com.websystem.libspace.domain.livro_ebook.LivroEbookUpdateDTO;
import com.websystem.libspace.service.LivroEbookService;
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
@RequestMapping("/livro_ebook")
public class LivroEbookController {

    @Autowired
    private LivroEbookService livroEbookService;

    @Operation(
            summary = "Criação de um novo registro do tipo livro ebook.",
            description = "Cria um novo registro de livro ebook com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro ebook criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<LivroEbookResponseDTO> create(@RequestBody @Valid LivroEbookRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        LivroEbookResponseDTO livroEbookResponseDTO = livroEbookService.create(body);

        var uri = uriComponentsBuilder.path("/livro_ebook/{id}").buildAndExpand(livroEbookResponseDTO.id_livro()).toUri();

        return ResponseEntity.created(uri).body(livroEbookResponseDTO);

    }

    @Operation(
            summary = "Listagem de um único registro do tipo livro ebook.",
            description = "Lista um único registro de livro ebook com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro ebook encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Livro ebook não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LivroEbookResponseDTO> getById(@PathVariable Long id){

        LivroEbookResponseDTO livroEbookResponseDTO = new LivroEbookResponseDTO(livroEbookService.findById(id));

        return ResponseEntity.ok().body(livroEbookResponseDTO);

    }

    @Operation(
            summary = "Listagem de todos os registros do tipo livro ebook.",
            description = "Lista todos os registros de livro ebook persistidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros ebooks encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum livro ebook encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<LivroEbookResponseDTO>> findAll(){

        List<LivroEbookResponseDTO> livroEbookResponseDTOList = livroEbookService.findAll();

        return ResponseEntity.ok().body(livroEbookResponseDTOList);

    }

    @Operation(
            summary = "Atualização de um registro do tipo livro ebook.",
            description = "Atualiza um registro de livro ebook com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro ebook atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "404", description = "Livro ebook não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid LivroEbookUpdateDTO body){

        livroEbookService.update(body);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Deleção de um determinado livro ebook.",
            description = "Realiza a deleção de um livro ebook específico com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro ebook deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Livro ebook não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){

        livroEbookService.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}
