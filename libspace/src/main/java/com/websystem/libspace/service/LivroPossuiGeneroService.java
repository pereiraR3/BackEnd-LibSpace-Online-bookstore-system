package com.websystem.libspace.service;

import com.websystem.libspace.domain.genero.Genero;
import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.livro_possui_genero.LivroPossuiGenero;
import com.websystem.libspace.domain.livro_possui_genero.LivroPossuiGeneroResponseDTO;
import com.websystem.libspace.repository.LivroPossuiGeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroPossuiGeneroService {

    @Autowired
    private LivroPossuiGeneroRepository livroPossuiGeneroRepository;

    @Autowired
    private LivroService livroService;

    @Autowired
    private GeneroService generoService;

    public LivroPossuiGeneroResponseDTO create(Long idLivro, Long idCategoria){

        Livro livro = livroService.findById(idLivro);

        Genero genero = generoService.findById(idCategoria);

        LivroPossuiGenero livroPossuiGenero = new LivroPossuiGenero(livro, genero);

        livroPossuiGeneroRepository.save(livroPossuiGenero);

        return new LivroPossuiGeneroResponseDTO(livroPossuiGenero);

    }

    public List<LivroPossuiGeneroResponseDTO> findByLivroId(Long idLivro){


        generoService.findById(idLivro);

        return livroPossuiGeneroRepository.findAllByLivroId(idLivro).stream().map(LivroPossuiGeneroResponseDTO::new).toList();

    }

    public List<LivroPossuiGeneroResponseDTO> findByCategoriaId(Long idCategoria){

        generoService.findById(idCategoria);

        return livroPossuiGeneroRepository.findAllByGeneroId(idCategoria).stream().map(LivroPossuiGeneroResponseDTO::new).toList();

    }

    private LivroPossuiGenero findByLivroIdCategoriaId(Long idLivro, Long idCategoria) {


        Livro livro = livroService.findById(idLivro);

        Genero genero = generoService.findById(idCategoria);

        return livroPossuiGeneroRepository.findByLivroIdAndGeneroId(livro.getId(), genero.getId());

    }

    public List<LivroPossuiGeneroResponseDTO> findAll(){

        return livroPossuiGeneroRepository.findAll().stream().map(LivroPossuiGeneroResponseDTO::new).collect(Collectors.toList());

    }

    public void deleteByLivroIdByGeneroId(Long idLivro, Long idCategoria){

        Livro livro = livroService.findById(idLivro);

        Genero genero = generoService.findById(idCategoria);

        livroPossuiGeneroRepository.deleteByLivroIdAndGeneroId(idLivro, idCategoria);

    }



}
