package com.websystem.libspace.domain.genero;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    @Mapping(target = "nome", source = "nome")
    void updateGeneroDTO(GeneroUpdateDTO updateDTO, @MappingTarget Genero genero);

}
