package com.websystem.libspace.service;

import com.websystem.libspace.domain.impostos.*;
import com.websystem.libspace.repository.ImpostosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImpostosService {

    @Autowired
    private ImpostosRepository impostosRepository;

    @Autowired
    private ImpostosMapper impostosMapper;

    public ImpostosResponseDTO create(ImpostosRequestDTO body){

        Impostos impostos = new Impostos(body);

        impostosRepository.save(impostos);

        return new ImpostosResponseDTO(impostos);

    }

    public Impostos findById(Long id){

        return impostosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Imposto não encontrado."));

    }

    public List<ImpostosResponseDTO> findAll(){

        return impostosRepository.findAll().stream().map(ImpostosResponseDTO::new).collect(Collectors.toList());

    }

    public void update(ImpostosUpdateDTO updateDTO){

        Impostos impostos = findById(updateDTO.id());
        impostosMapper.updateImpostosDTO(updateDTO, impostos);

    }

    public void deleteById(Long id){

        Impostos impostos  = findById(id);
        impostosRepository.deleteById(impostos.getId());

    }

}
