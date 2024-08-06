package com.websystem.libspace.service;

import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.livro_ebook.LivroEbook;
import com.websystem.libspace.domain.livro_ebook.LivroEbookRequestDTO;
import com.websystem.libspace.domain.livro_ebook.LivroEbookResponseDTO;
import com.websystem.libspace.domain.livro_ebook.LivroEbookUpdateDTO;
import com.websystem.libspace.repository.LivroEbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroEbookService {

    @Autowired
    private LivroEbookRepository livroEbookRepository;

    @Autowired
    private EditoraService editoraService;

    @Autowired
    private LivroService livroService;

    public LivroEbookResponseDTO create(LivroEbookRequestDTO body){

        Livro livro = livroService.findById(body.id());

        LivroEbook livroEbook = new LivroEbook(body, livro);

        livroEbookRepository.save(livroEbook);

        return new LivroEbookResponseDTO(livroEbook);

    }

    public LivroEbook findById(Long id){

        return livroEbookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LivroEbook não encontrado."));

    }

    public List<LivroEbookResponseDTO> findAll(){

        return livroEbookRepository.findAll().stream().map(LivroEbookResponseDTO::new).toList();

    }


    public void update(LivroEbookUpdateDTO body){

        LivroEbook livroEbook = findById(body.id_livro());
        livroEbook.update(body);

    }

    public void deleteById(Long id){

        LivroEbook livroEbook = findById(id);
        livroEbookRepository.deleteById(livroEbook.getLivro().getId());

    }

}
