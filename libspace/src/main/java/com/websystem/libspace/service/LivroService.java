package com.websystem.libspace.service;

import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.livro.LivroResponseDTO;
import com.websystem.libspace.domain.livro.LivroUpdateDTO;
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

    public Livro findById(Long id){

        return livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public List<LivroResponseDTO> findAll(){

        return livroRepository.findAll().stream().map(LivroResponseDTO::new).collect(Collectors.toList());

    }

    public void update(LivroUpdateDTO updateDTO){

        try{
            Livro livro = findById(updateDTO.id());
            livro.update(updateDTO);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    public void deleteById(Long id){

        try{
            Livro livro = findById(id);
            livroRepository.deleteById(id);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }

    }


}
