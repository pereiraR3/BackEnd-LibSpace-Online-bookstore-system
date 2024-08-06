package com.websystem.libspace.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.websystem.libspace.domain.editora.Editora;
import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.livro.LivroRequestDTO;
import com.websystem.libspace.repository.EditoraRepository;
import com.websystem.libspace.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LivroControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    private Editora editora;

    @BeforeEach
    public void setUp() {
        editora = new Editora();
        editora.setId(1L);
        editora.setNome("Editora Exemplo");
        editora.setCnpj(12345678000100L);
        editora.setCep(12345678L);
        editora.setTelefone("12345678901");
        editora.setEmail("contato@editoraexemplo.com");
        editora.setUrl_website("http://www.editoraexemplo.com");

        editoraRepository.save(editora);
    }

    //// - Test Method Create - ////

    @Test
    @DisplayName("Should create a new Entity Livro")
    void createSuccessfully() throws Exception {
        LivroRequestDTO livroRequestDTO = new LivroRequestDTO(
                editora.getId(),
                19.99,
                "Harry Potter",
                (short) 100,
                "J.K. Rowling",
                (short) 1997,
                "http://example.com/capa.jpg"
        );

        String jsonRequest = objectMapper.writeValueAsString(livroRequestDTO);

        mockMvc.perform(post("/livro/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());

        Livro livro = livroRepository.findByTitulo("Harry Potter");
        assertEquals("Harry Potter", livro.getTitulo());
        assertEquals("J.K. Rowling", livro.getAutor_nome());
    }


    @Test
    @DisplayName("Not should create a new Entity (Return Bad Request)")
    void createFailureBadRequest() throws Exception {

        LivroRequestDTO livroRequestDTO = new LivroRequestDTO(
                editora.getId(),
                10.0,
                "",
                (short) 100,
                "J.K. Rowling",
                (short) 1834,
                "http://example.com/capa.jpg"
        );

        String jsonRequest = objectMapper.writeValueAsString(livroRequestDTO);

        mockMvc.perform(post("/livro/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("Not should create a new Entity (Return Conflict)")
    void createFailureConflict() throws Exception {

        LivroRequestDTO livroRequestDTO1 = new LivroRequestDTO(
                editora.getId(),
                10.0,
                "Harry Potter",
                (short) 100,
                "J.K. Rowling",
                (short) 1834,
                "http://example.com/capa.jpg"
        );

        Livro livro = new Livro(livroRequestDTO1, editora);
        livroRepository.save(livro);

        LivroRequestDTO livroRequestDTO2 = new LivroRequestDTO(
                editora.getId(),
                10.0,
                "Harry Potter",
                (short) 100,
                "J.K. Rowling",
                (short) 1834,
                "http://example.com/capa.jpg"
        );

        String jsonRequest = objectMapper.writeValueAsString(livroRequestDTO2);

        mockMvc.perform(post("/livro/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }

    //// - Test Method Get (findById) - ////

    @Test
    @DisplayName("Should return an Entity Livro that Exists")
    void getSucessfullyObject() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/livro/{id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(1L)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

}

