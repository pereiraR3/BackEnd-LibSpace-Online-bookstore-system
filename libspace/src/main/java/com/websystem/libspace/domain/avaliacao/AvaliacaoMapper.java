package com.websystem.libspace.domain.avaliacao;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {

    @Mapping(target = "nota", source = "nota")
    @Mapping(target = "comentario", source = "comentario")
    void updateAvaliacaoDTO(AvaliacaoUpdateDTO updateDTO, @MappingTarget Avaliacao avaliacao);

}
