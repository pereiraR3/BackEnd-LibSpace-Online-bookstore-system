package com.websystem.libspace.controller;

import com.websystem.libspace.domain.livro.LivroRequestDTO;
import com.websystem.libspace.domain.livro.LivroResponseDTO;
import com.websystem.libspace.domain.livro.LivroUpdateDTO;
import com.websystem.libspace.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<LivroResponseDTO> create(@RequestBody @Valid LivroRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        LivroResponseDTO livroResponseDTO = livroService.create(body);

        var uri = uriComponentsBuilder.path("/create/{id}").buildAndExpand(livroResponseDTO.id()).toUri();

        return ResponseEntity.created(uri).body(livroResponseDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> getById(@PathVariable Long id){

        LivroResponseDTO livroResponseDTO = new LivroResponseDTO(livroService.findById(id));

        return ResponseEntity.ok().body(livroResponseDTO);

    }

    @GetMapping("/findAll")
    public ResponseEntity<List<LivroResponseDTO>> findAll(){

        List<LivroResponseDTO> livroResponseDTOList = livroService.findAll();

        return ResponseEntity.ok().body(livroResponseDTOList);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid LivroUpdateDTO updateDTO){

        livroService.update(updateDTO);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){

        livroService.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
