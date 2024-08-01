package com.websystem.libspace.controller;

import com.websystem.libspace.domain.oferta.OfertaResponseDTO;
import com.websystem.libspace.domain.oferta.OfertaUpdateDTO;
import com.websystem.libspace.service.OfertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oferta")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @Operation(
            summary = "Atualização de um registro do tipo oferta.",
            description = "Atualiza um registro de oferta com base nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Oferta atualizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "404", description = "Oferta não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody OfertaUpdateDTO updateDTO) {
        ofertaService.update(updateDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Listagem de um único registro do tipo oferta.",
            description = "Lista um único registro de oferta com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Oferta encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Oferta não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OfertaResponseDTO> findById(@PathVariable Long id) {
        OfertaResponseDTO ofertaResponseDTO = new OfertaResponseDTO(ofertaService.findById(id));
        return ResponseEntity.ok().body(ofertaResponseDTO);
    }

    @Operation(
            summary = "Listagem de todos os registros do tipo oferta.",
            description = "Lista todos os registros de oferta."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ofertas encontradas com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhuma oferta encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<OfertaResponseDTO>> findAll() {
        List<OfertaResponseDTO> ofertaResponseDTOList = ofertaService.findAll();
        return ResponseEntity.ok().body(ofertaResponseDTOList);
    }

    @Operation(
            summary = "Deleção de um registro do tipo oferta.",
            description = "Deleta um registro de oferta com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Oferta deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Oferta não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ofertaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
