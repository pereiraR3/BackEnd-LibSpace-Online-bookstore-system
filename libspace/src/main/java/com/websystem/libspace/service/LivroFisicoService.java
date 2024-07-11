package com.websystem.libspace.service;

import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.livro_fisico.LivroFisico;
import com.websystem.libspace.domain.livro_fisico.LivroFisicoRequestDTO;
import com.websystem.libspace.domain.livro_fisico.LivroFisicoResponseDTO;
import com.websystem.libspace.domain.livro_fisico.LivroFisicoUpdateDTO;
import com.websystem.libspace.repository.LivroFisicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroFisicoService {

    @Autowired
    private LivroFisicoRepository livroFisicoRepository;

    @Autowired
    private LivroService livroService;

    public LivroFisicoResponseDTO create(LivroFisicoRequestDTO body){

        Livro livro = livroService.findById(body.id());

        LivroFisico livroFisico = new LivroFisico(body, livro);

        livroFisicoRepository.save(livroFisico);

        return new LivroFisicoResponseDTO(livroFisico);

    }

    public LivroFisico findById(Long id){

        return livroFisicoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public List<LivroFisicoResponseDTO> findAll(){
        return livroFisicoRepository.findAll().stream().map(LivroFisicoResponseDTO::new).toList();
    }

    public void update(LivroFisicoUpdateDTO body){

        LivroFisico livroFisico = findById(body.id_livro());
        livroFisico.update(body);

    }

    public void deleteById(Long id){

        LivroFisico livroFisico = findById(id);
        livroFisicoRepository.deleteById(livroFisico.getId());

    }


}
