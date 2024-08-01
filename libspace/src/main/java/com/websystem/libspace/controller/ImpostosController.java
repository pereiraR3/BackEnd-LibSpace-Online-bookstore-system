package com.websystem.libspace.controller;

import com.websystem.libspace.domain.impostos.ImpostosRequestDTO;
import com.websystem.libspace.domain.impostos.ImpostosResponseDTO;
import com.websystem.libspace.domain.impostos.ImpostosUpdateDTO;
import com.websystem.libspace.service.ImpostosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/impostos")
public class ImpostosController {

    @Autowired
    private ImpostosService impostosService;

    @Operation(summary = "Cria um novo imposto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Imposto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<ImpostosResponseDTO> create(
            @RequestBody @Valid ImpostosRequestDTO body,
            UriComponentsBuilder uriComponentsBuilder) {

        ImpostosResponseDTO impostosResponseDTO = impostosService.create(body);

        var uri = uriComponentsBuilder.path("/impostos/{id}")
                .buildAndExpand(impostosResponseDTO.id()).toUri();

        return ResponseEntity.created(uri).body(impostosResponseDTO);
    }

    @Operation(summary = "Atualiza um imposto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Imposto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Imposto não encontrado")
    })
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<Void> update(@RequestBody @Valid ImpostosUpdateDTO updateDTO) {
        impostosService.update(updateDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Busca um imposto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imposto encontrado"),
            @ApiResponse(responseCode = "404", description = "Imposto não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ImpostosResponseDTO> findById(
            @Parameter(description = "ID do imposto a ser buscado")
            @PathVariable Long id) {
        ImpostosResponseDTO impostosResponseDTO = new ImpostosResponseDTO(impostosService.findById(id));
        return ResponseEntity.ok().body(impostosResponseDTO);
    }

    @Operation(summary = "Busca todos os impostos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de impostos encontrada")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<ImpostosResponseDTO>> findAll() {
        List<ImpostosResponseDTO> impostosResponseDTOList = impostosService.findAll();
        return ResponseEntity.ok().body(impostosResponseDTOList);
    }

    @Operation(summary = "Deleta um imposto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Imposto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Imposto não encontrado")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do imposto a ser deletado")
            @PathVariable Long id) {
        impostosService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
