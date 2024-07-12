package com.websystem.libspace.service;

import com.websystem.libspace.domain.categoria.Categoria;
import com.websystem.libspace.domain.editora.Editora;
import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.livro.LivroRequestDTO;
import com.websystem.libspace.domain.livro.LivroResponseDTO;
import com.websystem.libspace.domain.livro.LivroUpdateDTO;
import com.websystem.libspace.domain.livro_possui_categoria.LivroPossuiCategoria;
import com.websystem.libspace.domain.livro_possui_categoria.LivroPossuiCategoriaResponseDTO;
import com.websystem.libspace.repository.LivroAudiobookRepository;
import com.websystem.libspace.repository.LivroPossuiCategoriaRepository;
import com.websystem.libspace.repository.LivroRepository;
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

    public List<LivroPossuiCategoria> findByLivroId(Long idLivro){

        try{

            livroService.findById(idLivro);

            return livroPossuiCategoriaRepository.findByLivroId(idLivro);

        }catch(Exception e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }

    }

    public List<LivroPossuiCategoria> findByCategoriaId(Long idCategoria){

        try{

            categoriaService.findById(idCategoria);

            return livroPossuiCategoriaRepository.findByCategoriaId(idCategoria);

        }catch(Exception e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }

    }

    private LivroPossuiCategoria findByLivroIdCategoriaId(Long idLivro, Long idCategoria) {

        try{

            Livro livro = livroService.findById(idLivro);

            Categoria categoria = categoriaService.findById(idCategoria);

            return livroPossuiCategoriaRepository.findByLivroIdCategoriaId(livro.getId(), categoria.getId());

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }


    public List<LivroPossuiCategoriaResponseDTO> findAll(){

        return livroPossuiCategoriaRepository.findAll().stream().map(LivroPossuiCategoriaResponseDTO::new).collect(Collectors.toList());

    }

    public void deleteById(Long idLivro, Long idCategoria){

        LivroPossuiCategoria livroPossuiCategoria = findByLivroIdCategoriaId(idLivro, idCategoria);
        livroPossuiCategoriaRepository.deleteByLivroIdByCategoriaId(livroPossuiCategoria.getLivro().getId(), livroPossuiCategoria.getCategoria().getId());

    }

    public void deleteByLivroIdByCategoriaId(Long idLivro, Long idCategoria){

        Livro livro = livroService.findById(idLivro);

        Categoria categoria = categoriaService.findById(idCategoria);

        livroPossuiCategoriaRepository.deleteByLivroIdByCategoriaId(idLivro, idCategoria);

    }



}
