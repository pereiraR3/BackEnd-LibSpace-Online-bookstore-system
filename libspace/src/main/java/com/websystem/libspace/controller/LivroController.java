package com.websystem.libspace.controller;

import com.websystem.libspace.domain.livro.*;
import com.websystem.libspace.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Operation(
            summary = "Criação de um novo registro do tipo livro.",
            description = "Cria um novo registro de livro com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<LivroResponseDTO> create(@RequestBody @Valid LivroRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        LivroResponseDTO livroResponseDTO = livroService.create(body);

        var uri = uriComponentsBuilder.path("/livro/{id}").buildAndExpand(livroResponseDTO.id()).toUri();

        return ResponseEntity.created(uri).body(livroResponseDTO);

    }

    @Operation(
            summary = "Listagem de um único registro do tipo livro.",
            description = "Lista um único registro de livro com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> getById(@PathVariable Long id){

        LivroResponseDTO livroResponseDTO = new LivroResponseDTO(livroService.findById(id));

        return ResponseEntity.ok().body(livroResponseDTO);

    }

    @Operation(
            summary = "Listagem de todos os registros do tipo livro.",
            description = "Lista todos os registros de livro persistidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/findAll")
    public ResponseEntity<Page<LivroResponseDTO>> findAll(Pageable pageable){

        Page<LivroResponseDTO> livroResponseDTOList = livroService.findAll(pageable);

        if(livroResponseDTOList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(livroResponseDTOList);
    }

    @GetMapping("/findByAllLivroWithImpostos")
    public ResponseEntity<Page<LivroImpostosListeningDTO>> findByAllLivroWithImpostos(Pageable pageable){

        Page<LivroImpostosListeningDTO> livroImpostosListeningDTO = livroService.findByAllLivroWithImpostos(pageable);

        if(livroImpostosListeningDTO.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(livroImpostosListeningDTO);
    }

    @GetMapping("/findByLivroWithImpostos/{id}")
    public ResponseEntity<LivroImpostosListeningDTO> findByLivroWithImpostos(@PathVariable Long id){

        LivroImpostosListeningDTO livroImpostosListeningDTO = livroService.findByLivroWithImpostos(id);

        return ResponseEntity.ok().body(livroImpostosListeningDTO);
    }

    @GetMapping("/findAllLivroWithCategorias")
    public ResponseEntity<Page<LivroCategoriaListeningDTO>> findAllLivroWithCategorias(Pageable pageable){

        Page<LivroCategoriaListeningDTO> livroCategoriaListeningDTOS = livroService.findAllLivroWithCategorias(pageable);

        if(livroCategoriaListeningDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(livroCategoriaListeningDTOS);
    }


    @GetMapping("/findOnlyLivroWithCategorias/{id}")
    public ResponseEntity<LivroCategoriaListeningDTO> findOnlyLivroWithCategorias(@PathVariable Long id){

        LivroCategoriaListeningDTO livroCategoriaListeningDTO = livroService.findOnlyLivroWithCategorias(id);

        return ResponseEntity.ok().body(livroCategoriaListeningDTO);
    }

    @GetMapping("/findAllLivroWithGeneros")
    public ResponseEntity<Page<LivroWithGenerosListeningDTO>> findAllLivroWithGeneros(Pageable pageable){

        Page<LivroWithGenerosListeningDTO> livroWithGenerosListeningDTOS = livroService.findAllLivroWithGeneros(pageable);

        if(livroWithGenerosListeningDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(livroWithGenerosListeningDTOS);
    }


    @GetMapping("/findOnlyLivroWithGeneros/{id}")
    public ResponseEntity<LivroWithGenerosListeningDTO> findOnlyLivroWithGeneros(@PathVariable Long id){

        LivroWithGenerosListeningDTO livroWithGenerosListeningDTO = livroService.findOnlyLivroWithGeneros(id);

        return ResponseEntity.ok().body(livroWithGenerosListeningDTO);

    }

    @GetMapping("/findAllLivroWithCategoriasWIthGeneros")
    public ResponseEntity<Page<LivroWithGenerosAndCategoriasListeningDTO>> findAllLivroWithCategoriasWIthGeneros(Pageable pageable){

        Page<LivroWithGenerosAndCategoriasListeningDTO> livroWithGenerosAndCategoriasListeningDTOS = livroService.findAllLivroWithCategoriasWIthGeneros(pageable);

        if(livroWithGenerosAndCategoriasListeningDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(livroWithGenerosAndCategoriasListeningDTOS);
    }


    @Operation(
            summary = "Atualização de um registro do tipo livro.",
            description = "Atualiza um registro de livro com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid LivroUpdateDTO updateDTO){

        livroService.update(updateDTO);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Deleção de um determinado livro.",
            description = "Realiza a deleção de um livro específico com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){

        livroService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
