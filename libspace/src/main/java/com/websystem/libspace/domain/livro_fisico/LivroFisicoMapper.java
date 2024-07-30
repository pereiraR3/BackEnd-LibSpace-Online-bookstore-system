package com.websystem.libspace.domain.livro_fisico;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LivroFisicoMapper {

    @Mapping(target = "numero_de_paginas", source = "numero_de_paginas")
    @Mapping(target = "peso", source = "peso")
    @Mapping(target = "tipo_capa", source = "tipo_capa")
    @Mapping(target = "dimensao_altura", source = "dimensao_altura")
    @Mapping(target = "dimensao_profundidade", source = "dimensao_profundidade")
    void updateLivroFisicoMapper(LivroFisicoUpdateDTO updateDTO, @MappingTarget LivroFisico livroFisico);

}
