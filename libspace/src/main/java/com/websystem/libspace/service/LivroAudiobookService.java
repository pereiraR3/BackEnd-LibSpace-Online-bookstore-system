package com.websystem.libspace.service;

import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.livro_audiobook.*;
import com.websystem.libspace.repository.LivroAudiobookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroAudiobookService {

    @Autowired
    private LivroAudiobookRepository livroAudiobookRepository;

    @Autowired
    private EditoraService editoraService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroAudiobookMapper audiobookMapper;

    public LivroAudiobookResponseDTO create(LivroAudiobookRequestDTO  body){

        Livro livro = livroService.findById(body.id());

        LivroAudiobook livroAudiobook = new LivroAudiobook(body, livro);

        livroAudiobookRepository.save(livroAudiobook);

        return new LivroAudiobookResponseDTO(livroAudiobook);

    }

    public LivroAudiobook findById(Long id){

        return livroAudiobookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LivroAudiobook não encontrado."));

    }

    public List<LivroAudiobookResponseDTO> findAll(){

        return livroAudiobookRepository.findAll().stream().map(LivroAudiobookResponseDTO::new).toList();

    }


    public void update(LivroAudiobookUpdateDTO updateDTO){

        LivroAudiobook livroAudiobook = findById(updateDTO.id_livro());
        audiobookMapper.updateLivroAudibookDTO(updateDTO, livroAudiobook);

    }

    public void deleteById(Long id){

        LivroAudiobook livroAudiobook = findById(id);
        livroAudiobookRepository.deleteById(livroAudiobook.getLivro().getId());

    }


}
