package com.websystem.libspace.service;

import com.websystem.libspace.domain.categoria.*;
import com.websystem.libspace.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    public CategoriaResponseDTO create(CategoriaRequestDTO body){

        Categoria categoria = new Categoria(body);

        categoriaRepository.save(categoria);

        return new CategoriaResponseDTO(categoria);

    }

    public Categoria findById(Long id){

        return categoriaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public List<CategoriaResponseDTO> findAll(){

        return categoriaRepository.findAll().stream().map(CategoriaResponseDTO::new).collect(Collectors.toList());

    }

    public void update(CategoriaUpdateDTO updateDTO){

        Categoria categoria = findById(updateDTO.id());
        categoriaMapper.updateCategoriaDTO(updateDTO, categoria);

    }

    public void deleteById(Long id){

        Categoria categoria = findById(id);
        categoriaRepository.deleteById(id);


    }

}
