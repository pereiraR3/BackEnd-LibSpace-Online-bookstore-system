package com.websystem.libspace.service;

import com.websystem.libspace.domain.editora.Editora;
import com.websystem.libspace.domain.livro.*;
import com.websystem.libspace.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EditoraService editoraService;

    @Autowired
    private LivroMapper livroMapper;

    public LivroResponseDTO create(LivroRequestDTO body){

        Editora editora = editoraService.findById(body.id_editora());

        Livro livro = new Livro(body, editora);

        livroRepository.save(livro);

        return new LivroResponseDTO(livro);

    }

    public Livro findById(Long id){

        return livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public List<LivroResponseDTO> findAll(){

        return livroRepository.findAll().stream().map(LivroResponseDTO::new).collect(Collectors.toList());

    }

    public void update(LivroUpdateDTO updateDTO){

        Livro livro = findById(updateDTO.id());
        livroMapper.updateLivroFromDTO(updateDTO, livro);

    }

    public void deleteById(Long id){

        Livro livro = findById(id);
        livroRepository.deleteById(id);

    }


}
