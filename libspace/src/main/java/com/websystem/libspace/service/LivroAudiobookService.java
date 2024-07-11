package com.websystem.libspace.service;

import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.livro_audiobook.LivroAudiobook;
import com.websystem.libspace.domain.livro_audiobook.LivroAudiobookRequestDTO;
import com.websystem.libspace.domain.livro_audiobook.LivroAudiobookResponseDTO;
import com.websystem.libspace.domain.livro_audiobook.LivroAudiobookUpdateDTO;
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

    public LivroAudiobookResponseDTO create(LivroAudiobookRequestDTO  body){

        Livro livro = livroService.findById(body.id());

        LivroAudiobook livroAudiobook = new LivroAudiobook(body, livro);

        livroAudiobookRepository.save(livroAudiobook);

        return new LivroAudiobookResponseDTO(livroAudiobook);

    }

    public LivroAudiobook findById(Long id){

        return livroAudiobookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public List<LivroAudiobookResponseDTO> findAll(){

        return livroAudiobookRepository.findAll().stream().map(LivroAudiobookResponseDTO::new).toList();

    }


    public void update(LivroAudiobookUpdateDTO body){

        LivroAudiobook livroAudiobook = findById(body.id_livro());
        livroAudiobook.update(body);

    }

    public void deleteById(Long id){

        LivroAudiobook livroAudiobook = findById(id);
        livroAudiobookRepository.deleteById(livroAudiobook.getLivro().getId());

    }


}
