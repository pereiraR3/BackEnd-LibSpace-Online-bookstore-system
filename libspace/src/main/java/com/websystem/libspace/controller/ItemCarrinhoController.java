package com.websystem.libspace.controller;

import com.websystem.libspace.domain.item_carrinho.ItemCarrinhoRequestDTO;
import com.websystem.libspace.domain.item_carrinho.ItemCarrinhoResponseDTO;
import com.websystem.libspace.domain.item_carrinho.ItemCarrinhoUpdateDTO;
import com.websystem.libspace.service.ItemCarrinhoService;
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
@RequestMapping("/item_carrinho")
public class ItemCarrinhoController {

    @Autowired
    private ItemCarrinhoService itemCarrinhoService;

    @Operation(
            summary = "Criação de um novo item no carrinho.",
            description = "Cria um novo item no carrinho com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item no carrinho criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<ItemCarrinhoResponseDTO> create(@RequestBody @Valid ItemCarrinhoRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        ItemCarrinhoResponseDTO itemCarrinhoResponseDTO = itemCarrinhoService.create(body);
        var uri = uriComponentsBuilder.path("/item_carrinho/{id}").buildAndExpand(itemCarrinhoResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(itemCarrinhoResponseDTO);
    }

    @Operation(
            summary = "Atualização de um item no carrinho.",
            description = "Atualiza um item no carrinho com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item no carrinho atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "404", description = "Item no carrinho não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid ItemCarrinhoUpdateDTO updateDTO) {
        itemCarrinhoService.update(updateDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Listagem de um único item no carrinho.",
            description = "Lista um único item no carrinho com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item no carrinho encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Item no carrinho não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemCarrinhoResponseDTO> findById(@PathVariable Long id) {
        ItemCarrinhoResponseDTO itemCarrinhoResponseDTO = new ItemCarrinhoResponseDTO(itemCarrinhoService.findById(id));
        return ResponseEntity.ok().body(itemCarrinhoResponseDTO);
    }

    @Operation(
            summary = "Listagem de todos os itens no carrinho.",
            description = "Lista todos os itens no carrinho."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Itens no carrinho encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum item no carrinho encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<ItemCarrinhoResponseDTO>> findAll() {
        List<ItemCarrinhoResponseDTO> itemCarrinhoResponseDTOList = itemCarrinhoService.findAll();
        return ResponseEntity.ok().body(itemCarrinhoResponseDTOList);
    }

    @Operation(
            summary = "Deleção de um item no carrinho.",
            description = "Deleta um item no carrinho com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item no carrinho deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Item no carrinho não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        itemCarrinhoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
