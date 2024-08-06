package com.websystem.libspace.service;

import com.websystem.libspace.domain.editora.*;
import com.websystem.libspace.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private EditoraMapper editoraMapper;

    public EditoraResponseDTO create(EditoraRequestDTO body){

        Editora editora = new Editora(body);

        editoraRepository.save(editora);

        return new EditoraResponseDTO(editora);

    }

    public Editora findById(Long id){

        return editoraRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Editora não encontrada."));

    }

    public List<EditoraResponseDTO> findAll(){

        return editoraRepository.findAll().stream().map(EditoraResponseDTO::new).collect(Collectors.toList());

    }

    public void update(EditoraUpdateDTO updateDTO){

       Editora editora = findById(updateDTO.id());
       editoraMapper.updateEditoraDTO(updateDTO, editora);

    }

    public void deleteById(Long id){

        Editora editora = findById(id);
        editoraRepository.deleteById(id);

    }


}
