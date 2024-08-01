package com.websystem.libspace.service;

import com.websystem.libspace.domain.oferta.Oferta;
import com.websystem.libspace.domain.oferta.OfertaMapper;
import com.websystem.libspace.domain.oferta.OfertaResponseDTO;
import com.websystem.libspace.domain.oferta.OfertaUpdateDTO;
import com.websystem.libspace.repository.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private OfertaMapper ofertaMapper;

    public Oferta findById(Long id){
        return ofertaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Oferta não encontrada"));
    }

    public List<OfertaResponseDTO> findAll(){
        return ofertaRepository.findAll().stream().map(OfertaResponseDTO::new).toList();
    }

    public void update(OfertaUpdateDTO updateDTO){
        Oferta oferta = findById(updateDTO.id());
        ofertaMapper.updateOfertaDTO(updateDTO, oferta);
    }

    public void delete(Long id){
        Oferta oferta = findById(id);
        ofertaRepository.deleteById(oferta.getId());
    }

}
