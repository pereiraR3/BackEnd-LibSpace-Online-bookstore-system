package com.websystem.libspace.domain.categoria;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(target = "nome", source = "nome")
    void updateCategoriaDTO(CategoriaUpdateDTO updateDTO, @MappingTarget Categoria categoria);

}
