package com.websystem.libspace.controller;

import com.websystem.libspace.domain.users.UserRequestDTO;
import com.websystem.libspace.domain.users.UserResponseDTO;
import com.websystem.libspace.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // EndPoint - "/user/create"
    @Operation(
            summary = "Criação de um novo registro do tipo user.",
            description = "Cria um novo registro de user com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        UserResponseDTO UserResponseDTO = userService.create(body);
        var uri = uriComponentsBuilder.path("/categoria/{id}").buildAndExpand(UserResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(UserResponseDTO);
    }

    // EndPoint - "/user/{id}"
    @Operation(
            summary = "Listagem de um único registro do tipo user.",
            description = "Lista um único registro de user com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "User não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id){
        UserResponseDTO userResponseDTO = new UserResponseDTO(userService.findById(id));
        return ResponseEntity.ok().body(userResponseDTO);
    }

    // EndPoint - "/categoria/findAll"
    @Operation(
            summary = "Listagem de todos os registros do tipo user.",
            description = "Listagem de todos os registro de user com base nas informações persistidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponseDTO>> getAllCategoria(){
        List<UserResponseDTO> userResponseDTOList = userService.findAll();
        return ResponseEntity.ok().body(userResponseDTOList);
    }




}
