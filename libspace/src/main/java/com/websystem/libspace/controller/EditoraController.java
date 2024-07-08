package com.websystem.libspace.controller;

import com.websystem.libspace.domain.editora.Editora;
import com.websystem.libspace.domain.editora.EditoraRequestDTO;
import com.websystem.libspace.domain.editora.EditoraResponseDTO;
import com.websystem.libspace.domain.editora.EditoraUpdateDTO;
import com.websystem.libspace.service.EditoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/editora")
public class EditoraController {

    @Autowired
    private EditoraService editoraService;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<EditoraResponseDTO> create(@RequestBody @Valid EditoraRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        EditoraResponseDTO editoraResponseDTO = editoraService.create(body);

        var uri = uriComponentsBuilder.path("/create/{id}").buildAndExpand(editoraResponseDTO.id()).toUri();

        return ResponseEntity.created(uri).body(editoraResponseDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EditoraResponseDTO> getById(@PathVariable Long id){

        EditoraResponseDTO editoraResponseDTO = new EditoraResponseDTO(editoraService.findById(id));

        return ResponseEntity.ok().body(editoraResponseDTO);

    }

    @GetMapping("/findAll")
    public ResponseEntity<List<EditoraResponseDTO>> getAllEditora(){

        List<EditoraResponseDTO> editoraResponseDTOList = editoraService.findAll();

        return ResponseEntity.ok().body(editoraResponseDTOList);

    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid EditoraUpdateDTO updateDTO){

        editoraService.update(updateDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){

        editoraService.delete(id);

        return ResponseEntity.noContent().build();

    }


}
