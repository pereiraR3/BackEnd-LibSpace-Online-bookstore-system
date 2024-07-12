package com.websystem.libspace.controller;

import com.websystem.libspace.domain.livro_audiobook.LivroAudiobookRequestDTO;
import com.websystem.libspace.domain.livro_audiobook.LivroAudiobookResponseDTO;
import com.websystem.libspace.domain.livro_audiobook.LivroAudiobookUpdateDTO;
import com.websystem.libspace.service.LivroAudiobookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/livro_audiobook")
public class LivroAudiobookController {

    @Autowired
    private LivroAudiobookService livroAudiobookService;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<LivroAudiobookResponseDTO> create(@RequestBody @Valid LivroAudiobookRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        LivroAudiobookResponseDTO livroAudiobookResponseDTO = livroAudiobookService.create(body);

        var uri = uriComponentsBuilder.path("/create/{id}").buildAndExpand(livroAudiobookResponseDTO).toUri();

        return ResponseEntity.created(uri).body(livroAudiobookResponseDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroAudiobookResponseDTO> getById(@PathVariable Long id){

        LivroAudiobookResponseDTO livroAudiobookResponseDTO = new LivroAudiobookResponseDTO(livroAudiobookService.findById(id));

        return ResponseEntity.ok().body(livroAudiobookResponseDTO);

    }

    @GetMapping("/findAll")
    public ResponseEntity<List<LivroAudiobookResponseDTO>> findAll(){

        List<LivroAudiobookResponseDTO> livroAudiobookResponseDTOList = livroAudiobookService.findAll();

        return ResponseEntity.ok().body(livroAudiobookResponseDTOList);

    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid LivroAudiobookUpdateDTO body){

        livroAudiobookService.update(body);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){

        livroAudiobookService.deleteById(id);

        return ResponseEntity.noContent().build();

    }


}
