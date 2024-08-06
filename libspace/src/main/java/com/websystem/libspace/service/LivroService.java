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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

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

        Optional<Livro> livroOptinal = Optional.ofNullable(livroRepository.findById(id));
        return livroOptinal.orElseGet(() -> livroOptinal.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

    }

    /**
     * Listagem paginada de todos os livros com apenas os seus atributos
     * @return List<LivroResponseDTO>
     */
    public Page<LivroResponseDTO> findAll(Pageable pageable) {
        Page<Livro> livroPage = livroRepository.findAll(pageable);
        List<LivroResponseDTO> livroResponseDTOList = livroPage.stream()
                .map(LivroResponseDTO::new)
                .collect(Collectors.toList());

        return new PageImpl<>(livroResponseDTOList, pageable, livroPage.getTotalElements());
    }

    /**
     * Listagem paginada de todos os livros com os seus devidos impostos
     * @return Page<LivroImpostosListeningDTO>
     */
    public Page<LivroImpostosListeningDTO> findByAllLivroWithImpostos(Pageable pageable){

        Page<Livro> results = livroRepository.findAll(pageable);

        Map<Livro, List<Impostos>> livroListMap = results
                .stream()
                .collect(Collectors.groupingBy(
                        result -> result,
                        Collectors.mapping(result -> (Impostos) result.getImpostos(), Collectors.toList())
                ));

        List<LivroImpostosListeningDTO> livros = livroListMap.entrySet()
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

        return new PageImpl<>(livros, pageable, results.getTotalElements());

    }

    /**
     * Listagem do livro + todos os impostos associados ao mesmo
     * @param idLivro
     * @return LivroImpostosListeningDTO
     */
    public LivroImpostosListeningDTO findByLivroWithImpostos(Long idLivro) {

        Livro results = (Livro) livroRepository.findById(idLivro);

        List<Impostos> impostosList = (List<Impostos>) results.getImpostos().stream().toList();

        return new LivroImpostosListeningDTO(
                results.getId(),
                results.getEditora().getId(),
                results.getPreco_unitario(),
                results.getPreco_acumulado(),
                results.getTitulo(),
                results.getQuantidade(),
                impostosList.stream().map(ImpostosResponseDTO::new).collect(Collectors.toList())
        );
    }

    /**
     * Listagem paginada de todos os livros com as suas devidas categorias
     * @return Page<LivroCategoriaListeningDTO>
     */
    public Page<LivroCategoriaListeningDTO> findAllLivroWithCategorias(Pageable pageable){

        Page<Livro> results = livroRepository.findAll(pageable);

        Map<Livro, List<Categoria>> livroListMap = results
                .stream()
                .collect(Collectors.groupingBy(
                                result -> (Livro) result,
                                Collectors.mapping(result -> (Categoria) result.getCategorias(), Collectors.toList())
                        ));

        List<LivroCategoriaListeningDTO> listeningDTOS = livroListMap.entrySet()
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
                )).toList();

        return new PageImpl<>(listeningDTOS, pageable, results.getTotalElements());

    }

    /**
     * Listagem de um único livro com todas as suas categorias
     * @param idLivro
     * @return LivroCategoriaListeningDTO
     */
    public LivroCategoriaListeningDTO findOnlyLivroWithCategorias(Long idLivro){

        Livro results = livroRepository.findById(idLivro);
        List<Categoria> categorias = results.getCategorias().stream().toList();

        return new LivroCategoriaListeningDTO(
                results.getId(),
                results.getEditora().getId(),
                results.getPreco_unitario(),
                results.getPreco_acumulado(),
                results.getTitulo(),
                results.getQuantidade(),
                categorias.stream().map(CategoriaResponseDTO::new).toList()
        );

    }

    /**
     * Listagem de todos os livros e todos os seus respectivos generos associados
     * @return Page<LivroWithGenerosListeningDTO>
     */
    public Page<LivroWithGenerosListeningDTO> findAllLivroWithGeneros(Pageable pageable){

        Page<Livro> results = livroRepository.findAll(pageable);

        Map<Livro, List<Genero>> livroListMap = results
                .stream()
                .collect(Collectors.groupingBy(result -> (Livro) result,
                        Collectors.mapping(result -> (Genero) result.getGeneros(), Collectors.toList())));

        List<LivroWithGenerosListeningDTO> livroWithGenerosListeningDTOList = livroListMap.entrySet()
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

        return new PageImpl<>(livroWithGenerosListeningDTOList, pageable, results.getTotalElements());
    }

    /**
     * Listagem de um único livro com todos os seus respectivos generos
     * @param idLivro
     * @return LivroWithGenerosListeningDTO
     */
    public LivroWithGenerosListeningDTO findOnlyLivroWithGeneros(Long idLivro){

        Livro livro = livroRepository.findById(idLivro);

        List<Genero> generos = (List<Genero>) livro.getGeneros().stream().toList();

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


    /**
     * Listagem paginada de todas as categorias e generos de cada livro
     * @param pageable
     * @return Page<LivroWithGenerosAndCategoriasListeningDTO>
     */
    public Page<LivroWithGenerosAndCategoriasListeningDTO> findAllLivroWithCategoriasWIthGeneros(Pageable pageable) {

        Page<Livro> results = livroRepository.findAll(pageable);

        Map<Livro, List<Genero>> livroGeneroMap = new HashMap<>();
        Map<Livro, List<Categoria>> livroCategoriaMap = new HashMap<>();

        for (Livro result : results) {
            Genero genero = (Genero) result.getGeneros();
            Categoria categoria = (Categoria) result.getCategorias();

            livroGeneroMap.computeIfAbsent(result, k -> new ArrayList<>()).add(genero);
            livroCategoriaMap.computeIfAbsent(result, k -> new ArrayList<>()).add(categoria);
        }

        List<LivroWithGenerosAndCategoriasListeningDTO> livroWithGenerosAndCategoriasListeningDTOS = livroGeneroMap.entrySet().stream()
                .map(entry -> new LivroWithGenerosAndCategoriasListeningDTO(
                        entry.getKey().getId(),
                        entry.getKey().getEditora().getId(),
                        entry.getKey().getPreco_unitario(),
                        entry.getKey().getPreco_acumulado(),
                        entry.getKey().getTitulo(),
                        entry.getKey().getQuantidade(),
                        entry.getValue().stream().map(GeneroResponseDTO::new).collect(Collectors.toList()),
                        livroCategoriaMap.getOrDefault(entry.getKey(), new ArrayList<>()).stream().map(CategoriaResponseDTO::new).collect(Collectors.toList())
                )).toList();

        return new PageImpl<>(livroWithGenerosAndCategoriasListeningDTOS, pageable, results.getTotalElements());

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
     * Deleção relacional da entidade livro
     * @param id
     */
    public void deleteById(Long id) {

        Livro livro = findById(id);
        livroRepository.deleteById(livro.getId());

    }
}
