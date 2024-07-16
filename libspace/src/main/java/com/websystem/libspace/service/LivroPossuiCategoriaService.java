package com.websystem.libspace.service;

import com.websystem.libspace.domain.categoria.Categoria;
import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.livro_possui_categoria.LivroPossuiCategoria;
import com.websystem.libspace.domain.livro_possui_categoria.LivroPossuiCategoriaResponseDTO;
import com.websystem.libspace.repository.LivroPossuiCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroPossuiCategoriaService {

    @Autowired
    private LivroPossuiCategoriaRepository livroPossuiCategoriaRepository;

    @Autowired
    private LivroService livroService;

    @Autowired
    private CategoriaService categoriaService;

    public LivroPossuiCategoriaResponseDTO create(Long idLivro, Long idCategoria){

        Livro livro = livroService.findById(idLivro);

        Categoria categoria = categoriaService.findById(idCategoria);

        LivroPossuiCategoria livroPossuiCategoria = new LivroPossuiCategoria(livro, categoria);

        livroPossuiCategoriaRepository.save(livroPossuiCategoria);

        return new LivroPossuiCategoriaResponseDTO(livroPossuiCategoria);

    }

    public List<LivroPossuiCategoriaResponseDTO> findByLivroId(Long idLivro){


        livroService.findById(idLivro);

        return livroPossuiCategoriaRepository.findAllByLivroId(idLivro).stream().map(LivroPossuiCategoriaResponseDTO::new).toList();

    }

    public List<LivroPossuiCategoriaResponseDTO> findByCategoriaId(Long idCategoria){

        categoriaService.findById(idCategoria);

        return livroPossuiCategoriaRepository.findAllByCategoriaId(idCategoria).stream().map(LivroPossuiCategoriaResponseDTO::new).toList();

    }

    private LivroPossuiCategoria findByLivroIdCategoriaId(Long idLivro, Long idCategoria) {


        Livro livro = livroService.findById(idLivro);

        Categoria categoria = categoriaService.findById(idCategoria);

        return livroPossuiCategoriaRepository.findByLivroIdAndCategoriaId(livro.getId(), categoria.getId());

    }

    public List<LivroPossuiCategoriaResponseDTO> findAll(){

        return livroPossuiCategoriaRepository.findAll().stream().map(LivroPossuiCategoriaResponseDTO::new).collect(Collectors.toList());

    }

    public void deleteByLivroIdByCategoriaId(Long idLivro, Long idCategoria){

        Livro livro = livroService.findById(idLivro);

        Categoria categoria = categoriaService.findById(idCategoria);

        livroPossuiCategoriaRepository.deleteByLivroIdAndCategoriaId(idLivro, idCategoria);

    }



}
