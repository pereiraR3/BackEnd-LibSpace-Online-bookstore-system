package com.websystem.libspace.service;

import com.websystem.libspace.domain.avaliacao.Avaliacao;
import com.websystem.libspace.domain.avaliacao.AvaliacaoRequestDTO;
import com.websystem.libspace.domain.avaliacao.AvaliacaoResponseDTO;
import com.websystem.libspace.domain.avaliacao.AvaliacaoUpdateDTO;
import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.users.User;
import com.websystem.libspace.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LivroService livroService;

    public AvaliacaoResponseDTO create(AvaliacaoRequestDTO body){


        Livro livro = livroService.findById(body.id_livro());

        User user = userService.findById(body.id_user());

        Avaliacao avaliacao = new Avaliacao(body,  livro, user);

        avaliacaoRepository.save(avaliacao);

        return new AvaliacaoResponseDTO(avaliacao);

    }

    public Avaliacao findById(Long id){

        return avaliacaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public List<AvaliacaoResponseDTO> findAll(){

        return avaliacaoRepository.findAll().stream().map(AvaliacaoResponseDTO::new).collect(Collectors.toList());

    }

    public void update(AvaliacaoUpdateDTO updateDTO){

        Avaliacao avaliacao = findById(updateDTO.id());
        avaliacao.update(updateDTO);

    }

    public void deleteById(Long id){

        Avaliacao avaliacao = findById(id);
        avaliacaoRepository.deleteById(id);


    }

}
