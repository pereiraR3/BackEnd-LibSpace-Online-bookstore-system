package com.websystem.libspace.service;

import com.websystem.libspace.domain.editora.*;
import com.websystem.libspace.domain.livro.LivroListeningDTO;
import com.websystem.libspace.domain.livro.LivroResponseDTO;
import com.websystem.libspace.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    public EditoraResponseDTO create(EditoraRequestDTO body){

        Editora editora = new Editora(body);

        editoraRepository.save(editora);

        return new EditoraResponseDTO(editora);

    }

    public Editora findById(Long id){

        return editoraRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public List<EditoraResponseDTO> findAll(){

        return editoraRepository.findAll().stream().map(EditoraResponseDTO::new).collect(Collectors.toList());

    }

    public void update(EditoraUpdateDTO updateDTO){

        try{
            Editora editora = findById(updateDTO.id());
            editora.update(updateDTO);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    public void deleteId(Long id){

        try{
            Editora editora = findById(id);
            editoraRepository.deleteById(id);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }

    }


}
