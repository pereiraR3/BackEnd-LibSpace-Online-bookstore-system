package com.websystem.libspace.controller;


import com.websystem.libspace.domain.categoria.CategoriaRequestDTO;
import com.websystem.libspace.domain.categoria.CategoriaResponseDTO;
import com.websystem.libspace.domain.categoria.CategoriaUpdateDTO;
import com.websystem.libspace.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<CategoriaResponseDTO> create(@RequestBody @Valid CategoriaRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        CategoriaResponseDTO categoriaResponseDTO = categoriaService.create(body);

        var uri = uriComponentsBuilder.path("/create/{id}").buildAndExpand(categoriaResponseDTO).toUri();

        return ResponseEntity.created(uri).body(categoriaResponseDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable Long id){

        CategoriaResponseDTO categoriaResponseDTO = new CategoriaResponseDTO(categoriaService.findById(id));

        return ResponseEntity.ok().body(categoriaResponseDTO);

    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CategoriaResponseDTO>> getAllEditora(){

        List<CategoriaResponseDTO> categoriaResponseDTOList = categoriaService.findAll();

        return ResponseEntity.ok().body(categoriaResponseDTOList);

    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid CategoriaUpdateDTO updateDTO){

        categoriaService.update(updateDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){

        categoriaService.deleteById(id);

        return ResponseEntity.noContent().build();

    }


}
