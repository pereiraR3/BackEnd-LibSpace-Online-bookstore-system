package com.websystem.libspace.controller;

import com.websystem.libspace.domain.livro_ebook.LivroEbookRequestDTO;
import com.websystem.libspace.domain.livro_ebook.LivroEbookResponseDTO;
import com.websystem.libspace.service.LivroEbookService;
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

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<LivroEbookResponseDTO>  create(@RequestBody @Valid LivroEbookRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        LivroEbookResponseDTO livroEbookResponseDTO = livroEbookService.create(body);

        var uri = uriComponentsBuilder.path("/create/{id}").buildAndExpand(livroEbookResponseDTO).toUri();

        return ResponseEntity.created(uri).body(livroEbookResponseDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroEbookResponseDTO> getById(@PathVariable Long id){

        LivroEbookResponseDTO livroEbookResponseDTO = new LivroEbookResponseDTO(livroEbookService.findById(id));

        return ResponseEntity.ok().body(livroEbookResponseDTO);

    }

    @GetMapping("/findAll")
    public ResponseEntity<List<LivroEbookResponseDTO>> findAll(){

        List<LivroEbookResponseDTO> livroEbookResponseDTOList = livroEbookService.findAll();

        return ResponseEntity.ok().body(livroEbookResponseDTOList);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){

        livroEbookService.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}
