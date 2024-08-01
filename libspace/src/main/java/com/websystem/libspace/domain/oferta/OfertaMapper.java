package com.websystem.libspace.domain.oferta;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OfertaMapper {

    @Mapping(target = "preco_revenda", source = "preco_revenda")
    @Mapping(target = "desconto", source = "desconto")
    void updateOfertaDTO(OfertaUpdateDTO updateDTO, @MappingTarget Oferta oferta);

}
