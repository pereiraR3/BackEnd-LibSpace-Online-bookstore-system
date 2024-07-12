package com.websystem.libspace.controller;

import com.websystem.libspace.domain.livro_fisico.LivroFisicoRequestDTO;
import com.websystem.libspace.domain.livro_fisico.LivroFisicoResponseDTO;
import com.websystem.libspace.domain.livro_fisico.LivroFisicoUpdateDTO;
import com.websystem.libspace.service.LivroFisicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/livro_fisico")
public class LivroFisicoController {

    @Autowired
    private LivroFisicoService livroFisicoService;


    @PostMapping("/create")
    @Transactional
    public ResponseEntity<LivroFisicoResponseDTO> create(@RequestBody @Valid LivroFisicoRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        LivroFisicoResponseDTO livroFisicoResponseDTO = livroFisicoService.create(body);

        var uri = uriComponentsBuilder.path("/create/{id}").buildAndExpand(livroFisicoResponseDTO.id_livro()).toUri();

        return ResponseEntity.created(uri).body(livroFisicoResponseDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroFisicoResponseDTO> getById(@PathVariable Long id){

        LivroFisicoResponseDTO livroFisicoResponseDTO = new LivroFisicoResponseDTO(livroFisicoService.findById(id));

        return ResponseEntity.ok().body(livroFisicoResponseDTO);

    }

    @GetMapping("/findAll")
    public ResponseEntity<List<LivroFisicoResponseDTO>> findAll(){

        List<LivroFisicoResponseDTO> livroFisicoResponseDTOList = livroFisicoService.findAll();

        return ResponseEntity.ok().body(livroFisicoResponseDTOList);

    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid LivroFisicoUpdateDTO body){

        livroFisicoService.update(body);

        return ResponseEntity.noContent().build();

    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){

        livroFisicoService.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}
