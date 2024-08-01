package com.websystem.libspace.service;

import com.websystem.libspace.domain.categoria.Categoria;
import com.websystem.libspace.domain.categoria.CategoriaResponseDTO;
import com.websystem.libspace.domain.editora.Editora;
import com.websystem.libspace.domain.genero.Genero;
import com.websystem.libspace.domain.genero.GeneroResponseDTO;
import com.websystem.libspace.domain.impostos.Impostos;
import com.websystem.libspace.domain.impostos.ImpostosResponseDTO;
import com.websystem.libspace.domain.livro.*;
import com.websystem.libspace.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EditoraService editoraService;

    @Autowired
    private LivroMapper livroMapper;

    /**
     * Criando um novo livro
     * @param body
     * @return LivroResponseDTO
     */
    public LivroResponseDTO create(LivroRequestDTO body){

        Editora editora = editoraService.findById(body.id_editora());

        Livro livro = new Livro(body, editora);

        livroRepository.save(livro);

        return new LivroResponseDTO(livro);

    }

    /**
     * Busca por um livro específico por meio de uma Id
     * @param id
     * @return Livro
     */
    public Livro findById(Long id){

        return livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    /**
     * Listagem de todos os livros com apenas os seus atributos
     * @return List<LivroResponseDTO>
     */
    public List<LivroResponseDTO> findAll(){

        return livroRepository.findAll().stream().map(LivroResponseDTO::new).collect(Collectors.toList());

    }

    /**
     * Listagem de todos os livros com os seus devidos impostos
     * @return List<LivroImpostosListeningDTO>
     */
    public List<LivroImpostosListeningDTO> findByAllLivroWithImpostos(){

        List<Object[]> results = livroRepository.getLivroWithImpostos();

        Map<Livro, List<Impostos>> livroListMap = results
                .stream()
                .collect(Collectors.groupingBy(
                        result -> (Livro) result[0],
                        Collectors.mapping(result -> (Impostos) result[1], Collectors.toList())
                ));

        return livroListMap.entrySet()
                .stream()
                .map(obj -> new LivroImpostosListeningDTO(
                        obj.getKey().getId(),
                        obj.getKey().getEditora().getId(),
                        obj.getKey().getPreco_unitario(),
                        obj.getKey().getPreco_acumulado(),
                        obj.getKey().getTitulo(),
                        obj.getKey().getQuantidade(),
                        obj.getValue().stream().map(ImpostosResponseDTO::new).toList()
                        )
                ).collect(Collectors.toList());

    }

    /**
     * Listagem do livro + todos os impostos associados ao mesmo
     * @param idLivro
     * @return LivroImpostosListeningDTO
     */
    public LivroImpostosListeningDTO findByLivroWithImpostos(Long idLivro) {

        Object[] results = livroRepository.getOnlyLivroWithImpostos(idLivro);

        Livro livro = (Livro) results[0];
        List<Impostos> impostosList = (List<Impostos>) results[1];

        return new LivroImpostosListeningDTO(
                livro.getId(),
                livro.getEditora().getId(),
                livro.getPreco_unitario(),
                livro.getPreco_acumulado(),
                livro.getTitulo(),
                livro.getQuantidade(),
                impostosList.stream().map(ImpostosResponseDTO::new).collect(Collectors.toList())
        );
    }

    /**
     * Listagem de todos os livros com as suas devidas categorias
     * @return List<LivroCategoriaListeningDTO>
     */
    public List<LivroCategoriaListeningDTO> getLivroWithCategorias(){

        List<Object[]> results = livroRepository.getLivroWithCategorias();

        Map<Livro, List<Categoria>> livroListMap = results
                .stream()
                .collect(Collectors.groupingBy(
                                result -> (Livro) result[0],
                                Collectors.mapping(result -> (Categoria) result[1], Collectors.toList())
                        ));

        return livroListMap.entrySet()
                .stream()
                .map(obj -> new LivroCategoriaListeningDTO(
                        obj.getKey().getId(),
                        obj.getKey().getEditora().getId(),
                        obj.getKey().getPreco_unitario(),
                        obj.getKey().getPreco_acumulado(),
                        obj.getKey().getTitulo(),
                        obj.getKey().getQuantidade(),
                        obj.getValue().stream()
                                .map(CategoriaResponseDTO::new).toList()
                )).collect(Collectors.toList());
    }

    /**
     * Listagem de um único livro com todas as suas categorias
     * @param idLivro
     * @return LivroCategoriaListeningDTO
     */
    public LivroCategoriaListeningDTO getOnlyLivroWithCategorias(Long idLivro){

        Object[] results = livroRepository.getOnlyLivroWithCategorias(idLivro);

        Livro livro = (Livro) results[0];
        List<Categoria> categorias = (List<Categoria>) results[1];

        return new LivroCategoriaListeningDTO(
                livro.getId(),
                livro.getEditora().getId(),
                livro.getPreco_unitario(),
                livro.getPreco_acumulado(),
                livro.getTitulo(),
                livro.getQuantidade(),
                categorias.stream().map(CategoriaResponseDTO::new).toList()
        );

    }

    /**
     * Listagem de todos os livros e todos os seus respectivos generos associados
     * @return List<LivroWithGenerosListeningDTO>
     */
    public List<LivroWithGenerosListeningDTO> getLivroWithGeneros(){

        List<Object[]> results = livroRepository.getLivroWithGeneros();

        Map<Livro, List<Genero>> livroListMap = results
                .stream()
                .collect(Collectors.groupingBy(result -> (Livro) result[0],
                        Collectors.mapping(result -> (Genero) result[1], Collectors.toList())));

        return livroListMap.entrySet()
                .stream().map(obj -> new LivroWithGenerosListeningDTO(
                        obj.getKey().getId(),
                        obj.getKey().getEditora().getId(),
                        obj.getKey().getPreco_unitario(),
                        obj.getKey().getPreco_acumulado(),
                        obj.getKey().getTitulo(),
                        obj.getKey().getQuantidade(),
                        obj.getValue()
                                .stream()
                                .map(GeneroResponseDTO::new).toList()
                )).toList();

    }

    /**
     * Listagem de um único livro com todos os seus respectivos generos
     * @param idLivro
     * @return LivroWithGenerosListeningDTO
     */
    public LivroWithGenerosListeningDTO getOnlyLivroWithGeneros(Long idLivro){

        Object[] results = livroRepository.getOnlyLivroWithGeneros(idLivro);

        Livro livro = (Livro) results[0];
        List<Genero> generos = (List<Genero>) results[1];

        return new LivroWithGenerosListeningDTO(

                livro.getId(),
                livro.getEditora().getId(),
                livro.getPreco_unitario(),
                livro.getPreco_acumulado(),
                livro.getTitulo(),
                livro.getQuantidade(),
                generos
                        .stream()
                        .map(GeneroResponseDTO::new).toList()
        );

    }


    public List<LivroWithGenerosAndCategoriasListeningDTO> getAllLivroWithCategoriasWIthGeneros() {

        List<Object[]> results = livroRepository.getAllLivroWithGenerosWIthCategorias();

        // Usando dois mapas para agrupar os livros com suas categorias e gêneros
        Map<Livro, List<Genero>> livroGeneroMap = new HashMap<>();
        Map<Livro, List<Categoria>> livroCategoriaMap = new HashMap<>();

        for (Object[] result : results) {
            Livro livro = (Livro) result[0];
            Genero genero = (Genero) result[1];
            Categoria categoria = (Categoria) result[2];

            livroGeneroMap.computeIfAbsent(livro, k -> new ArrayList<>()).add(genero);
            livroCategoriaMap.computeIfAbsent(livro, k -> new ArrayList<>()).add(categoria);
        }

        // Convertendo os mapas para uma lista de DTOs
        return livroGeneroMap.entrySet().stream()
                .map(entry -> new LivroWithGenerosAndCategoriasListeningDTO(
                        entry.getKey().getId(),
                        entry.getKey().getEditora().getId(),
                        entry.getKey().getPreco_unitario(),
                        entry.getKey().getPreco_acumulado(),
                        entry.getKey().getTitulo(),
                        entry.getKey().getQuantidade(),
                        entry.getValue().stream().map(GeneroResponseDTO::new).collect(Collectors.toList()),
                        livroCategoriaMap.getOrDefault(entry.getKey(), new ArrayList<>()).stream().map(CategoriaResponseDTO::new).collect(Collectors.toList())
                )).collect(Collectors.toList());
    }



    /**
     * Update das informações de um livro conforme o UpdateDTO usando Mapper
     * @param updateDTO
     */
    public void update(LivroUpdateDTO updateDTO){

        Livro livro = findById(updateDTO.id());
        livroMapper.updateLivroFromDTO(updateDTO, livro);

    }

    /**
     * Deleção Relacional de um livro e seus associados
     * @param id
     */
    public void deleteById(Long id){

        Livro livro = findById(id);
        livroRepository.deleteById(id);

    }


}
