package com.websystem.libspace.service;

import com.websystem.libspace.domain.genero.*;
import com.websystem.libspace.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private GeneroMapper generoMapper;

    public GeneroResponseDTO create(GeneroRequestDTO body){

        Genero genero = new Genero(body);

        generoRepository.save(genero);

        return new GeneroResponseDTO(genero);

    }

    public Genero findById(Long id){

        return generoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genero não encontrado."));

    }

    public List<GeneroResponseDTO> findAll(){

        return generoRepository.findAll().stream().map(GeneroResponseDTO::new).collect(Collectors.toList());

    }

    public void update(GeneroUpdateDTO updateDTO){

        Genero genero  = findById(updateDTO.id());
        generoMapper.updateGeneroDTO(updateDTO, genero);

    }

    public void deleteById(Long id){

         Genero genero = findById(id);
        generoRepository.deleteById(genero.getId());

    }
}
