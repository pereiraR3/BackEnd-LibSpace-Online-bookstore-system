package com.websystem.libspace.controller;

import com.websystem.libspace.domain.carrinho.CarrinhoRequestDTO;
import com.websystem.libspace.domain.carrinho.CarrinhoResponseDTO;
import com.websystem.libspace.domain.carrinho.CarrinhoUpdateDTO;
import com.websystem.libspace.service.CarrinhoService;
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
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @Operation(
            summary = "Criação de um novo registro do tipo carrinho.",
            description = "Cria um novo registro de carrinho com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrinho criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<CarrinhoResponseDTO> create(@RequestBody @Valid CarrinhoRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        CarrinhoResponseDTO carrinhoResponseDTO = carrinhoService.create(body);
        var uri = uriComponentsBuilder.path("/carrinho/{id}").buildAndExpand(carrinhoResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(carrinhoResponseDTO);
    }

    @Operation(
            summary = "Atualização de um registro do tipo carrinho.",
            description = "Atualiza um registro de carrinho com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Carrinho atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "404", description = "Carrinho não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid CarrinhoUpdateDTO updateDTO) {
        carrinhoService.update(updateDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Listagem de um único registro do tipo carrinho.",
            description = "Lista um único registro de carrinho com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinho encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Carrinho não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoResponseDTO> findById(@PathVariable Long id) {
        CarrinhoResponseDTO carrinhoResponseDTO = new CarrinhoResponseDTO(carrinhoService.findById(id));
        return ResponseEntity.ok().body(carrinhoResponseDTO);
    }

    @Operation(
            summary = "Listagem de todos os registros do tipo carrinho.",
            description = "Lista todos os registros de carrinho."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinhos encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum carrinho encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<CarrinhoResponseDTO>> findAll() {
        List<CarrinhoResponseDTO> carrinhoResponseDTOList = carrinhoService.findAll();
        return ResponseEntity.ok().body(carrinhoResponseDTOList);
    }

    @Operation(
            summary = "Deleção de um registro do tipo carrinho.",
            description = "Deleta um registro de carrinho com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Carrinho deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Carrinho não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        carrinhoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
